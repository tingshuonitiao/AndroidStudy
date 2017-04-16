package com.example.tsnt.design_pattern.decorator;

/**
 * Created by ting说你跳 on 2017/4/10.
 */

public class ConcreteComponent extends Component {
    @Override
    public void operate() {
        System.out.println("I'm ZXZ");
    }

    public static void main(String[] args) {
        ConcreteComponent component = new ConcreteComponent();
        DecoratorA decoratorA = new DecoratorA();
        DecoratorB decoratorB = new DecoratorB();
        decoratorA.setComponent(component);
        decoratorB.setComponent(decoratorA);
        decoratorB.operate();
    }
}
