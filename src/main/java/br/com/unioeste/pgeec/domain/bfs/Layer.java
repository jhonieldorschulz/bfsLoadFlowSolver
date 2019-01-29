package br.com.unioeste.pgeec.domain.bfs;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhoni on 17/12/2017.
 */
public class Layer {

    Integer layer;

    private List<Branch> branches;

    private Complex sumCurrent;

    public Layer(Integer layer, List<Branch> branches) {
        this.layer = layer;
        this.branches = branches;
    }

    public Layer(Integer layer, Branch branch) {
        System.out.println("new Layer(Integer layer, Branch branch)....");
        System.out.println(branch.getOrigin().getNumber() + " ---> " + branch.getDestiny().getNumber());

        this.layer = layer;
        this.branches = new ArrayList<>();
        this.branches.add(branch);
    }

    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public Complex getSumCurrent() {
        return sumCurrent;
    }

    public void setSumCurrent(Complex sumCurrent) {
        this.sumCurrent = sumCurrent;
    }
}
