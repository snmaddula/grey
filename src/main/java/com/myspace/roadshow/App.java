package com.myspace.roadshow;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {

    public static void main(final String[] args) {
        ErrorResponses errors = new ErrorResponses();
        List<Term> terms = new ArrayList<>();
        Term term = new Term();
        term.setRepurchaseAgreementEndDate(new Date());
        term.setRepurchaseAgreementInterestRate(0);
        term.setRepurchaseAgreementStartAmount(100000);
        term.setRepurchaseAgreementEndAmount(100000);

        term.setRepurchaseAgreementStartDate(new Date());
        terms.add(term);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        StatelessKieSession kieSession = kieContainer.newStatelessKieSession("TermValidations");
        kieSession.setGlobal("errors", errors);
        System.out.println("==== DROOLS SESSION START ==== ");
        terms.forEach(_term -> {
            kieSession.execute(_term);
        });

        System.out.println("==== DROOLS SESSION END ==== ");

        System.out.println("\n\nERRORS:");
        errors.getErrors().forEach(System.out::println);

    }
}
