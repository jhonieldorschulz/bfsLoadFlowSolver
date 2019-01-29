package br.com.unioeste.pgeec;


import br.com.unioeste.pgeec.domain.bfs.BFS;
import br.com.unioeste.pgeec.domain.bfs.Branch;
import br.com.unioeste.pgeec.domain.bfs.Layer;
import br.com.unioeste.pgeec.domain.bfs.Node;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BFSTests2 {



    @Test
    public void testSistemaAlimentador1(){
        List<Node> nodes = new ArrayList<>();

        /**
         * Barra 2    kw=120.0    kvar=108.0    kv=11    prioridade=1
         Barra 3    kw=72.00    kvar=48.00    kv=11    prioridade=0,5
         Barra 4    kw=180.0    kvar=156.0    kv=11    prioridade=0,125
         Barra 5    kw=90.00    kvar=60.00    kv=11    prioridade=0,125
         Barra 6    kw=21.60    kvar=13.00    kv=11    prioridade=0,125
         Barra 7    kw=21.60    kvar=17.00    kv=11    prioridade=0,125
         Barra 8    kw=15.60    kvar=12.00    kv=11    prioridade=0,125
         Barra 9    kw=19.00    kvar=13.00    kv=11    prioridade=0,125
         Barra 10   kw=24.00    kvar=12.00    kv=11    prioridade=1
         Barra 11   kw=19.20    kvar=11.00    kv=11    prioridade=0,25
         Barra 12   kw=60.00    kvar=48.00    kv=11    prioridade=0,5
         Barra 13   kw=126.0    kvar=108.0    kv=11    prioridade=0,5
         Barra 14   kw=30.00    kvar=18.00    kv=11    prioridade=1
         Barra 15   kw=48.00    kvar=30.00    kv=11    prioridade=0,25
         Barra 68   kw=120.0    kvar=72.00    kv=11    prioridade=1
         Barra 69   kw=48.00    kvar=36.00    kv=11    prioridade=1

         */
        //0
        nodes.add(new Node(1, new Complex(0.0, 0.0), new Complex(11000, 0.0)));
        //1
        nodes.add(new Node(2, new Complex(120000.00, 108000.00), new Complex(11000, 0.0)));
        //2
        nodes.add(new Node(3, new Complex(72000, 48000), new Complex(11000.00, 0.0)));
        //3
        nodes.add(new Node(4, new Complex(180000.00, 156000), new Complex(11000.00, 0.0)));
        //4
        nodes.add(new Node(5, new Complex(90000.00, 60000.00), new Complex(11000.00, 0.0)));
        //5
        nodes.add(new Node(6, new Complex(21600.00, 13000.00), new Complex(11000.00, 0.0)));
        //6
        nodes.add(new Node(7, new Complex(21600.00, 17000.00), new Complex(11000.00, 0.0)));
        //7
        nodes.add(new Node(8, new Complex(15600.00, 12000.00), new Complex(11000.00, 0.0)));
        //8
        nodes.add(new Node(9, new Complex(19000.00, 13000.00), new Complex(11000.00, 0.0)));
        //9
        nodes.add(new Node(10, new Complex(24000.00, 12000.00), new Complex(11000.00, 0.0)));
        //10
        nodes.add(new Node(11, new Complex(19200.00, 11000.00), new Complex(11000.00, 0.0)));
        //11
        nodes.add(new Node(12, new Complex(60000.00, 48000.00), new Complex(11000.00, 0.0)));
        //12
        nodes.add(new Node(13, new Complex(126000.00, 108000.00), new Complex(11000.00, 0.0)));
        //13
        nodes.add(new Node(14, new Complex(30000.00, 18000.00), new Complex(11000.00, 0.0)));
        //14
        nodes.add(new Node(15, new Complex(48000.00, 30000.00), new Complex(11000.00, 0.0)));
        //15
        nodes.add(new Node(68, new Complex(120000.00, 72000.00), new Complex(11000.00, 0.0)));
        //16
        nodes.add(new Node(69, new Complex(48000.00, 36000.00), new Complex(11000.00, 0.0)));

//        nodes.add(new Node(6, new Complex(570000.00, 278969.532), new Complex(34500.00, 0.0)));

        List<Branch> branches = new ArrayList<>();

        List<Layer> layers = new ArrayList<>();
        /**
         * LAYER 1
         */
        branches.add(new Branch(nodes.get(0), nodes.get(1), new Complex(1.097, 1.074)));



        /**
         * LAYER 2
         */
        branches.add(new Branch(nodes.get(1), nodes.get(2), new Complex(1.463, 1.432)));


        /**
         * LAYER 3
         */
        branches.add(new Branch(nodes.get(2), nodes.get(3), new Complex(0.731, 0.716)));

        /**
         * LAYER 4
         */
        branches.add(new Branch(nodes.get(3), nodes.get(4), new Complex(0.366, 0.358)));
        branches.add(new Branch(nodes.get(3), nodes.get(9), new Complex(1.080, 0.734)));


        /**
         * LAYER 5
         */
        branches.add(new Branch(nodes.get(4), nodes.get(5), new Complex(1.828, 1.790)));
        branches.add(new Branch(nodes.get(9), nodes.get(10), new Complex(1.620, 1.101)));


        /**
         * LAYER 6
         */
        branches.add(new Branch(nodes.get(5), nodes.get(6), new Complex(1.097, 1.074)));
        branches.add(new Branch(nodes.get(10), nodes.get(11), new Complex(1.080, 0.734)));

        /**
         * LAYER 7
         */
        branches.add(new Branch(nodes.get(6), nodes.get(7), new Complex(0.731, 0.716)));
        branches.add(new Branch(nodes.get(6), nodes.get(15), new Complex(1.080, 0.734)));
        branches.add(new Branch(nodes.get(11), nodes.get(12), new Complex(1.350, 0.917)));

        /**
         * LAYER 8
         */
        branches.add(new Branch(nodes.get(7), nodes.get(8), new Complex(0.731, 0.716)));
        branches.add(new Branch(nodes.get(15), nodes.get(16), new Complex(1.620, 1.101)));
        branches.add(new Branch(nodes.get(12), nodes.get(13), new Complex(0.810, 0.550)));

        /**
         * LAYER 9
         */
        branches.add(new Branch(nodes.get(13), nodes.get(14), new Complex(1.944, 1.321)));



//        layers.add(new Layer(1, branches.subList(0, 1)));
//        layers.add(new Layer(2, branches.subList(1, 2)));
//        layers.add(new Layer(3, branches.subList(2, 3)));
//        layers.add(new Layer(4, branches.subList(3, 5)));
//        layers.add(new Layer(5, branches.subList(5, 7)));
//        layers.add(new Layer(6, branches.subList(7, 9)));
//        layers.add(new Layer(7, branches.subList(9, 12)));
//        layers.add(new Layer(8, branches.subList(12, 15)));
//        layers.add(new Layer(9, branches.subList(15, 16)));
//
//        System.out.println("-----------------------------------------");
//        System.out.println("-----------------------------------------");
//        layers.forEach(l -> {
//            System.out.println("Layer " + l.getLayer());
//            l.getBranches().forEach(b -> {
//                System.out.println(b.getOrigin().getNumber() + " ---> " + b.getDestiny().getNumber());
//            });
//        });

        BFS bfs = new BFS(branches, nodes);

        bfs.setMaxIterarions(50);

//
        bfs.iteratePowerFlow();

    }

    @Test
    public void testPhasor(){
        Complex complex = new Complex(0.9283695238095239, -0.02066285714285715);
        System.out.println(complex.acos());
    }

    @Test
    public void testSistema4Nodes(){

        List<Node> nodes = new ArrayList<>();

        /**
         * Cargas
         S2 = (1.28 + j1.28) pu = (12.8 + j12.8 )MVA
         S3 = (0.32 + j0.16) pu = (3.2  + j1.60 )MVA
         S4 = (1.6  + j0.80) pu = (16   + j8.00 )MVA
         */

        //S1
        nodes.add(new Node(1, new Complex(0.0, 0.0), new Complex(1.05, 0.0)));
        //S2
        nodes.add(new Node(2, new Complex(1.28 , 1.28), new Complex(1.05, 0.0)));
        //S3
        nodes.add(new Node(3, new Complex(0.32 , 0.16), new Complex(1.05, 0.0)));
        //S4
        nodes.add(new Node(4, new Complex(1.6 , 0.8), new Complex(1.05, 0.0)));


        List<Branch> branches = new ArrayList<>();

        List<Layer> layers = new ArrayList<>();

        /**
         * LAYER 1
         */
        branches.add(new Branch(nodes.get(0), nodes.get(1), new Complex(0.0236, 0.0233)));
        /**
         * LAYER 2
         */
        branches.add(new Branch(nodes.get(1), nodes.get(2), new Complex(0.0003, 0.0002)));
        /**
         * LAYER 3
         */
        branches.add(new Branch(nodes.get(2), nodes.get(3), new Complex(0.0051, 0.0005)));


//        layers.add(new Layer(1, branches.subList(0, 1)));
//        layers.add(new Layer(2, branches.subList(1, 2)));
//        layers.add(new Layer(3, branches.subList(2, 3)));
//
//        BFS bfs = new BFS(nodes, branches, layers);

        BFS bfs = new BFS(branches, nodes);

        bfs.setMaxIterarions(10);
        bfs.setPrintIterations(false);

        bfs.iteratePowerFlow();

    }


//    public Boolean isNewLayer(){
//        return
//    }








}
