package com.examp.lib.seclibng;

import android.content.res.Resources;
import android.view.View;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * An aspect for handling metrics logging for click events,user interactions etc
 */
@Aspect
public class MetricsAspect {
    private static final String TAGMETRICASPECT = "MetricAspect";
    private static final String CLICKEDON = "Clicked on ";
    private static final String SELECTED = "Selected ";

    /**
     * This pointcut and method will find any onClick method
     * and find the {@link View} associated with the click. Using the View's
     * {@link Resources} access the method finds the human-friendly ID of the
     * View and logs it.
     *
     * @param joinPoint {@link JoinPoint} representing the method being hijacked
     */
    @Before("execution(* onClick(..))")
    public void onClick(final JoinPoint joinPoint) {
            Object[] args = joinPoint.getArgs();
            if (args.length == 1) {
                if (args[0] instanceof View) {
                    final View view = (View) args[0];
                    System.out.println("Called clicked feature>> ");
                }
            }
    }

    /**
     * This pointcut and method will find doClick method supported by ButterKnife
     * and find the {@link View} associated with the click. Using the View's
     * {@link Resources} access the method finds the human-friendly ID of the
     * View and logs it.
     *
     * @param joinPoint {@link JoinPoint} representing the method being hijacked
     */
    @Before("execution(* doClick(..))")
    public void onButtonClick(final JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        if (args.length == 1) {
            if (args[0] instanceof View) {
                final View view = (View) args[0];
                System.out.println("Called clicked feature using ButterKnife>> ");
            }
        }
    }
}
