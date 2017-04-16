package com.example.tsnt.design_pattern.decorator;

/**
 * Created by ting说你跳 on 2017/4/10.
 */

abstract class Decorator extends Component {
    protected Component mComponent;

    public void setComponent(Component component) {
        mComponent = component;
    }

    @Override
    public void operate() {
        mComponent.operate();
    }
}
