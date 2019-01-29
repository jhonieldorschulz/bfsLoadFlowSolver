package br.com.unioeste.pgeec;


import br.com.unioeste.pgeec.domain.bfs.BFS;
import br.com.unioeste.pgeec.domain.bfs.Branch;
import br.com.unioeste.pgeec.domain.bfs.Layer;
import br.com.unioeste.pgeec.domain.bfs.Node;
import org.apache.commons.math3.complex.Complex;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhoni on 17/12/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BFSTests {

    private List<Node> nodes = new ArrayList<>();

    private List<Branch> branches = new ArrayList<>();

    private List<Layer> layers = new ArrayList<>();

    private BFS bfs = null;

    @Before
    public void init() {
        System.out.println("init()...");

        nodes.add(new Node(1, new Complex(0.0, 0.0), new Complex(34500.00, 0.0)));
        nodes.add(new Node(2, new Complex(570000.00, 278969.532), new Complex(34500.00, 0.0)));
        nodes.add(new Node(3, new Complex(570000.00, 278969.532), new Complex(34500.00, 0.0)));
        nodes.add(new Node(4, new Complex(570000.00, 278969.532), new Complex(34500.00, 0.0)));
        nodes.add(new Node(5, new Complex(570000.00, 278969.532), new Complex(34500.00, 0.0)));
        nodes.add(new Node(6, new Complex(570000.00, 278969.532), new Complex(34500.00, 0.0)));

        /**
         * LAYER 1
         */
        branches.add(new Branch(nodes.get(0), nodes.get(1), new Complex(0.936975, 0.72695)));
        /**
         * LAYER 2
         */
        branches.add(new Branch(nodes.get(1), nodes.get(2), new Complex(0.7254, 0.5628)));
        /**
         * LAYER 3
         */
        branches.add(new Branch(nodes.get(2), nodes.get(3), new Complex(0.538005, 0.41741)));


        /**
         * LAYER 4
         */
        branches.add(new Branch(nodes.get(3), nodes.get(4), new Complex(0.840255, 0.65191)));

        branches.add(new Branch(nodes.get(2), nodes.get(5), new Complex(0.574275, 0.44555)));


        System.out.println("Gerando layers...");
        for (Branch branch : branches) {
            System.out.println("Processando Branch: " + branch.getOrigin().getNumber() + " ---> " + branch.getDestiny().getNumber());
            if (layers.size() > 0) {
                System.out.println("Verificando layer existente...");
                Layer existentLayer = layers.stream().filter(bl -> bl.getBranches().stream()
                        .filter(b -> b.getOrigin().getNumber().equals(branch.getOrigin().getNumber()) &&
                                !b.getDestiny().getNumber().equals(branch.getDestiny().getNumber())).findAny().isPresent())
                        .findAny().orElse(null);

                System.out.println("Layer existente: " + (existentLayer != null));

                if (existentLayer != null) {
                    System.out.println("existentLayer - " + existentLayer.getLayer());
                    existentLayer.getBranches().add(branch);
                } else {

                    Layer layer = new Layer(layers.size() + 1, branch);
                    System.out.println("New Layer : " + layer.getLayer());
                    layers.add(layer);
                }
            } else {
                System.out.println("Layer 1");
                System.out.println("Branch: " + branch.getOrigin().getNumber() + " ---> " + branch.getDestiny().getNumber());
                layers.add(new Layer(1, branch));
            }
        }


//        layers.add(new Layer(1, branches.subList(0, 1)));
//        layers.add(new Layer(2, branches.subList(1, 2)));
//        layers.add(new Layer(3, branches.subList(2, 4)));
//        layers.add(new Layer(4, branches.subList(4, branches.size())));
//        System.out.println("-----------------------------------------");
//        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Layers: ");
        System.out.println("------------------------------------------------------------------------");
        layers.forEach(l -> {
            System.out.println("Layer " + l.getLayer());
            l.getBranches().forEach(b -> {
                System.out.println(b.getOrigin().getNumber() + " ---> " + b.getDestiny().getNumber());
            });
        });

//        bfs = new BFS(nodes, branches, layers);

//        bfs.setMaxIterarions(50);
    }

    @Test
    public void testLayers(){
        System.out.println("Test Layers...");
    }

    @Test
    public void testSimulation() {

        for (int it = 0; it < 2; it++) {
            System.out.println("It:" + it);
            System.out.println("----------------------------------------------");

            for (int i = layers.size() - 1; i >= 0; i--) {
                System.out.println("Layer " + layers.get(i).getLayer());
                System.out.println("---------------------------------------");

                layers.get(i).getBranches().forEach(branch -> {
                    System.out.println(branch.getOrigin().getNumber() + " ---> " + branch.getDestiny().getNumber());
                    branch.getOrigin().setCurrentFlow(
                            branch.getOrigin().getCurrentFlow().add(branch.getDestiny().getCurrentFlow())
                    );
                    branch.setCurrent(new Complex(branch.getDestiny().getCurrentFlow().getReal(),
                            branch.getDestiny().getCurrentFlow().getImaginary()));


                    branch.setIZ(branch.getCurrent().multiply(branch.getImpedance()));

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

                });
            }

            layers.forEach(layer -> {
                System.out.println("Layer " + layer.getLayer());
                System.out.println("---------------------------------------");
                layer.getBranches().forEach(branch -> {
                    System.out.println(branch.getOrigin().getNumber() + " ---> " + branch.getDestiny().getNumber());
                    System.out.println("branch.getOrigin().getVoltage():" + branch.getOrigin().getVoltage());
                    System.out.println("branch.getIZ():" + branch.getIZ());
                    System.out.println("branch.getCurrent():" + branch.getCurrent());
                    System.out.println("branch.getImpedance()" + branch.getImpedance());
                    branch.getDestiny().setVoltage(branch.getOrigin().getVoltage().subtract(branch.getIZ()));
                    System.out.println("branch.getDestiny().getVoltage():" + branch.getDestiny().getVoltage());
                });
            });

            nodes.forEach(node -> {
                System.out.println("Node: " + node.getNumber());
                System.out.println("----------------------------------");
                System.out.println("Voltage:" + node.getVoltage());
                System.out.println("Current Flow:" + node.getCurrentFlow());
                node.setInjectedCurrent(node.getPower().divide(node.getVoltage()).conjugate());
                node.setCurrentFlow(new Complex(node.getInjectedCurrent().getReal(), node.getInjectedCurrent().getImaginary()));
                System.out.println("Injected Current:" + node.getInjectedCurrent());

                Complex recalculatedPower = node.getVoltage().multiply(node.getInjectedCurrent());
                System.out.println("Recalculated Power:" + recalculatedPower);
                System.out.println("Aparent Power:" + node.getPower());

                System.out.println("Recalculated Power abs():" + recalculatedPower.abs());
                System.out.println("Aparent Power abs():" + node.getPower().abs());

                System.out.println("Checked convergence:" + Math.abs(node.getPower().abs() - recalculatedPower.abs()));
            });
        }
    }

    @Test
    public void iteratePowerFlow() {
        bfs.iteratePowerFlow();


    }

    @Test
    public void backwardTest() {
        for (int i = layers.size() - 1; i >= 0; i--) {
            System.out.println("Layer " + layers.get(i).getLayer());
            System.out.println("---------------------------------------");

            layers.get(i).getBranches().forEach(branch -> {
                System.out.println(branch.getOrigin().getNumber() + " ---> " + branch.getDestiny().getNumber());
                branch.getOrigin().setCurrentFlow(
                        branch.getOrigin().getCurrentFlow().add(branch.getDestiny().getCurrentFlow())
                );
                branch.setCurrent(new Complex(branch.getDestiny().getCurrentFlow().getReal(),
                        branch.getDestiny().getCurrentFlow().getImaginary()));


                branch.setIZ(branch.getCurrent().multiply(branch.getImpedance()));

                System.out.println("Current:");
                System.out.println("-------------------------------------------");
                System.out.println("current:" + branch.getCurrent());
                System.out.println("branch.getOrigin().getCurrentFlow():" + branch.getOrigin().getCurrentFlow());
                System.out.println("branch.getDestiny().getCurrentFlow():" + branch.getDestiny().getCurrentFlow());


            });
        }


        System.out.println("Current on branches.get(0):" + branches.get(0).getCurrent());
        System.out.println("Current Flow on nodes.get(0):" + nodes.get(0).getCurrentFlow());

    }
}
