package br.com.unioeste.pgeec.domain.bfs;

import org.apache.commons.math3.complex.Complex;

/**
 * Created by jhoni on 17/12/2017.
 */
public class Branch {

    private Node origin;

    private Node destiny;

    private Complex impedance;

    private Complex current;

    private Complex IZ;

    public Branch() {
    }

    public Branch(Node origin, Node destiny, Complex impedance) {
        this.origin = origin;
        this.destiny = destiny;
        this.impedance = impedance;
    }

    public Branch(Node origin, Node destiny, Complex impedance, Complex current) {
        this.origin = origin;
        this.destiny = destiny;
        this.impedance = impedance;
        this.current = current;
    }

    public Node getOrigin() {
        return origin;
    }

    public void setOrigin(Node origin) {
        this.origin = origin;
    }

    public Node getDestiny() {
        return destiny;
    }

    public void setDestiny(Node destiny) {
        this.destiny = destiny;
    }

    public Complex getImpedance() {
        return impedance;
    }

    public void setImpedance(Complex impedance) {
        this.impedance = impedance;
    }

    public Complex getCurrent() {
        return current;
    }

    public void setCurrent(Complex current) {
        this.current = current;
    }

    public Complex getIZ() {
        return IZ;
    }

    public void setIZ(Complex IZ) {
        this.IZ = IZ;
    }
}
