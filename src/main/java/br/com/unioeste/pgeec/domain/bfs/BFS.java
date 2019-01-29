package br.com.unioeste.pgeec.domain.bfs;

import org.apache.commons.math3.complex.Complex;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jhoni on 22/12/2017.
 */
public class BFS {

    private List<Branch> branches = new ArrayList<>();
    private List<Layer> layers = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();

    private Boolean converge = false;
    private Boolean printIterations = true;
    private Integer iterations = 0;


    private Integer maxIterarions;

    public BFS(List<Branch> branches, List<Node> nodes) {
        this.branches = branches;
        this.nodes = nodes;
        generateLayers();
        printLayers();
    }

    public BFS(List<Node> nodes, List<Branch> branches, List<Layer> layers) {
        this.nodes = nodes;
        this.branches = branches;
        this.layers = layers;


    }

    public void backward() {
        if(printIterations){
            System.out.println("BACKWARD");
            System.out.println("-----------------------------------------------------------------");
        }

        for (int i = layers.size() - 1; i >= 0; i--) {
            layers.get(i).getBranches().forEach(branch -> {
                branch.getOrigin().setCurrentFlow(
                        branch.getOrigin().getCurrentFlow().add(branch.getDestiny().getCurrentFlow())
                );
                branch.setCurrent(new Complex(branch.getDestiny().getCurrentFlow().getReal(),
                        branch.getDestiny().getCurrentFlow().getImaginary()));
                branch.setIZ(branch.getCurrent().multiply(branch.getImpedance()));

                if (printIterations) {
                    System.out.println("-------------------------------");
                    System.out.println("Branch: " + branch.getOrigin().getNumber() + " ---> " + branch.getDestiny().getNumber());

                    System.out.println("Current:");
                    System.out.println("-------------------------------------------");
                    System.out.println("current.abs():" + branch.getCurrent().abs());
                    System.out.println("current.getReal():" + branch.getCurrent().getReal());
                    System.out.println("current.getImag():" + branch.getCurrent().getImaginary());
                    System.out.println("current.AngRad():" + branch.getCurrent().log().getImaginary());
                    System.out.println("current.AngDeg():" + Math.toDegrees(branch.getCurrent().log().getImaginary()));

                    System.out.println("IZ:");
                    System.out.println("-------------------------------------------");
                    System.out.println("iz.abs():" + branch.getIZ().abs());
                    System.out.println("iz.getReal():" + branch.getIZ().getReal());
                    System.out.println("iz.getImag():" + branch.getIZ().getImaginary());
                    System.out.println("iz.AngRad():" + branch.getIZ().log().getImaginary());
                    System.out.println("iz.AngDeg():" + Math.toDegrees(branch.getIZ().log().getImaginary()));
                }

            });
        }
    }

    public void forward() {
        if (printIterations) {
            System.out.println("FORWARD");
            System.out.println("-----------------------------------------------------------------");
        }

        layers.forEach(layer -> {
            layer.getBranches().forEach(branch -> {
                if(printIterations){
                    System.out.println(branch.getOrigin().getNumber() + " ---> " + branch.getDestiny().getNumber());
                    System.out.println("branch.getOrigin().getVoltage():" + branch.getOrigin().getVoltage());
                    System.out.println("branch.getIZ():" + branch.getIZ());
                    System.out.println("branch.getCurrent():" + branch.getCurrent());

                    System.out.println("-------------------------------------------------");
                    System.out.println("abs: " + branch.getCurrent().abs() + " ang.: " + Math.toDegrees(branch.getCurrent().log().getImaginary()));
                    System.out.println("-------------------------------------------------");
                    System.out.println("branch.getImpedance()" + branch.getImpedance());
                }

                branch.getDestiny().setPreviousVoltage(branch.getDestiny().getVoltage().abs());
                branch.getDestiny().setVoltage(branch.getOrigin().getVoltage().subtract(branch.getIZ()));
                branch.getDestiny().updateNode();
//                branch.getDestiny().setInjectedCurrent(branch.getDestiny().getPower().divide(branch.getDestiny().getVoltage()).conjugate());
//
//                branch.getDestiny().setCurrentFlow(new Complex(branch.getDestiny().getInjectedCurrent().getReal(), branch.getDestiny().getInjectedCurrent().getImaginary()));
//                branch.getDestiny().setPower( branch.getDestiny().getVoltage().multiply( branch.getDestiny().getInjectedCurrent()).conjugate());

//                System.out.println("branch.getDestiny().getVoltage():" + branch.getDestiny().getVoltage());
//                System.out.println("branch.getDestiny().getPower():" + branch.getDestiny().getPower());
//                System.out.println("branch.getDestiny().getInjectedCurrent():" + branch.getDestiny().getInjectedCurrent());
            });
        });
        if(printIterations){
            System.out.println("-----------------------------------------------------------------");
        }

    }

    public void iteratePowerFlow() {
//
        while (!this.converge) {
            if (!converge) {
                if (printIterations) {
                    System.out.println("\n########################################################################");
                    System.out.println("\nIteração: " + (iterations + 1));
                    System.out.println("-------------------------------------");
                }

                backward();
                forward();
                checkIteration();

                iterations++;
                if (iterations.equals(maxIterarions)) {
                    break;
                }
            } else {
                break;
            }
            if(printIterations){
                System.out.println("-------------------------------------");
            }

        }

        System.out.println("----------------------------------------------------");
        System.out.println("Converge:" + converge);
        System.out.println("Iterations: " + iterations);

    }


    private void generateLayers() {
        for (Branch branch : branches) {
            System.out.println("Processando Branch: " + branch.getOrigin().getNumber() + " ---> " + branch.getDestiny().getNumber());
            if (isNewLayer(branch)) {
                layers.add(new Layer(layers.size() + 1, branch));
            } else {
                for (int i = 0; i < layers.size(); i++) {
                    if (i > 0) {
                        for (Branch previousLayerBranch : layers.get(i - 1).getBranches()) {
                            if (branch.getOrigin().getNumber().equals(previousLayerBranch.getDestiny().getNumber())) {
                                layers.get(i).getBranches().add(branch);
                            }
                        }

                    } else {
                        for (Branch b : layers.get(i).getBranches()) {
                            if (branch.getOrigin().getNumber().equals(b.getOrigin().getNumber())) {
                                layers.get(i).getBranches().add(branch);
                            }
                        }
                    }
                }
            }
        }
    }

    private Boolean isNewLayer(Branch branch) {
        Boolean isNewLayer = true;
        for (int i = 0; i < layers.size(); i++) {
            if (i > 0) {
                isNewLayer = !layers.get(i - 1).getBranches().stream().filter(b -> branch.getOrigin().getNumber().equals(b.getDestiny().getNumber()))
                        .findAny()
                        .isPresent();
            } else {
                isNewLayer = !layers.get(i).getBranches().stream().filter(b -> branch.getOrigin().getNumber().equals(b.getOrigin().getNumber()))
                        .findAny()
                        .isPresent();
            }
        }

        return isNewLayer;
    }

    private void printLayers() {
        for (Layer layer : layers) {
            System.out.println("\nLayer: " + layer.getLayer());
            System.out.println("-----------------------------------------");
            for (Branch branch : layer.getBranches()) {
                System.out.println("Ori.:[" + branch.getOrigin().getNumber() + "] ---> Dest.:[" + branch.getDestiny().getNumber() + "]");
            }
        }
    }


    public void checkIteration() {

        nodes.forEach(node -> {
            if (printIterations) {
                System.out.println("Node: " + node.getNumber());
                System.out.println("----------------------------------");
                System.out.println("Voltage:" + node.getVoltage());
                System.out.println("---------------------------------------------");
                System.out.println("abs.: " + node.getVoltage().abs() + " Ang. Deg.:" + Math.toDegrees(node.getVoltage().log().getImaginary()));
                System.out.println("abs.: " + node.getVoltage().abs() + " log.:" + node.getVoltage().log());
                System.out.println("abs.: " + node.getVoltage().abs() + " acos.:" + node.getVoltage().acos());
                System.out.println("abs.: " + node.getVoltage().abs() + " atan.:" + node.getVoltage().atan());
                System.out.println("abs.: " + node.getVoltage().abs() + " Math.atan.:" + Math.atan(node.getVoltage().getReal() / node.getVoltage().getReal()));
                System.out.println("---------------------------------------------");
                System.out.println("Current Flow:" + node.getCurrentFlow());
            }
            node.setInjectedCurrent(node.getPower().divide(node.getVoltage()).conjugate());
            node.setCurrentFlow(new Complex(node.getInjectedCurrent().getReal(), node.getInjectedCurrent().getImaginary()));
            if (printIterations) {
                System.out.println("Injected Current:" + node.getInjectedCurrent());
            }


        });

        List<Node> convergedNodes = new ArrayList<>();
        convergedNodes = nodes.stream()
                .filter(node -> node.getConvergedNode()).collect(Collectors.toList());

        if (printIterations) {
            System.out.println("Converged Nodes:" + convergedNodes.size());
        }


        this.converge = (convergedNodes.size() == (nodes.size() - 1));
    }

    public Integer getMaxIterarions() {
        return maxIterarions;
    }

    public void setMaxIterarions(Integer maxIterarions) {
        this.maxIterarions = maxIterarions;
    }


    public void setPrintIterations(Boolean printIterations) {
        this.printIterations = printIterations;
    }
}
