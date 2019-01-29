package br.com.unioeste.pgeec.domain.bfs;

import org.apache.commons.math3.complex.Complex;

/**
 * Created by jhoni on 17/12/2017.
 */
public class Node {

    private Integer number;

    private Complex power;

    private Complex voltage;

    private Complex injectedCurrent;

    private Complex currentFlow;

    private Boolean convergedNode = false;

    private Double previousVoltage;

    public Node(Integer number, Complex power, Complex voltage) {
        this.number = number;
        this.power = power;
        this.voltage = voltage;
        this.injectedCurrent = power.divide(voltage).conjugate();
        this.currentFlow = this.injectedCurrent;
    }

    public Node(Integer number, Complex power) {
        this.number = number;
        this.power = power;

    }

    public void updateNode(){

//        System.out.println("updateNode()....");
//        System.out.println("updatedVoltage: " + voltage.abs() + " Ang.:" + Math.toDegrees(voltage.log().getImaginary()));
//        System.out.println("----------------------------------------------------------");
//        System.out.println(voltage);
//        System.out.println("----------------------------------------------------------");

//        Complex previousPower = power;
//        System.out.println("previousPower:" + previousPower);
//        System.out.println("---------------------------------");
//        System.out.println("[" + previousPower.abs() + " < " + previousPower.acos() + "]");
//        System.out.println("---------------------------------");
        this.injectedCurrent = this.power.divide(this.voltage).conjugate();
//        System.out.println("injectedCurrent: " + injectedCurrent);
        this.currentFlow = new Complex(this.injectedCurrent.getReal(), this.injectedCurrent.getImaginary());
//        System.out.println("currentFlow:" + currentFlow);

//        this.power = this.voltage.multiply(this.injectedCurrent).conjugate();
//        System.out.println("updatedPower:" + power);
//        System.out.println("---------------------------------");
//        System.out.println("[" + power.abs() + " < " + power.acos() + "]");
//        System.out.println("---------------------------------");
        Double checkedConvergence = Math.abs(this.voltage.abs() - previousVoltage);



//        System.out.println("checkedConvergence: " + checkedConvergence);

        this.convergedNode = (checkedConvergence <=  0.0002);

//        System.out.println("convergedNode: " + convergedNode);

//        branch.getDestiny().setInjectedCurrent(branch.getDestiny().getPower().divide(branch.getDestiny().getVoltage()).conjugate());
//
//        branch.getDestiny().setCurrentFlow(new Complex(branch.getDestiny().getInjectedCurrent().getReal(), branch.getDestiny().getInjectedCurrent().getImaginary()));
//        branch.getDestiny().setPower( branch.getDestiny().getVoltage().multiply( branch.getDestiny().getInjectedCurrent()).conjugate());
    }

    public Double getPreviousVoltage() {
        return previousVoltage;
    }

    public void setPreviousVoltage(Double previousVoltage) {
//        System.out.println("setPreviousVoltage...");
//        System.out.println("previousVoltage: " + previousVoltage);
        this.previousVoltage = previousVoltage;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Complex getPower() {
        return power;
    }

    public void setPower(Complex power) {
        this.power = power;
    }

    public Complex getVoltage() {
        return voltage;
    }

    public void setVoltage(Complex voltage) {
        this.voltage = voltage;
    }

    public Complex getInjectedCurrent() {
        return injectedCurrent;
    }

    public void setInjectedCurrent(Complex injectedCurrent) {
        this.injectedCurrent = injectedCurrent;
    }

    public Complex getCurrentFlow() {
        return currentFlow;
    }

    public void setCurrentFlow(Complex currentFlow) {
        this.currentFlow = currentFlow;
    }

    public Boolean getConvergedNode() {
        return convergedNode;
    }

    public void setConvergedNode(Boolean convergedNode) {
        this.convergedNode = convergedNode;
    }
}
