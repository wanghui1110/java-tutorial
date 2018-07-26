package com.designpatterns.proxy;

import com.designpatterns.common.Food;

/**
 * 结构型模式 - 代理模式
 * 用一个代理来隐藏具体实现类的实现细节，通常还用于在真实的实现的前后添加一部分逻辑。
 * 代理要表现得“就像是”真实实现类，所以需要实现 FoodService
 *
 * @author Jarvis
 * @date 2018/7/26
 */
public class FoodServiceProxy implements FoodService {
    /**
     * step 1, 内部一定要有一个真实的实现类，当然也可以通过构造方法注入
     */
    private FoodService foodService = new FoodServiceImpl();

    @Override
    public Food createChicken() {
        System.out.println("我们马上要是开始制作鸡肉饭了。。。");
        // 如果我们定义这句为核心代码的话，那么，核心代码是真实实现类做的，
        // 代理只是在核心代码前后做些“无足轻重”的事情
        Food food = foodService.createChicken();
        // 增强
        System.out.println("鸡肉制作完成啦，加点胡椒粉");
        //food.addXXX()
        return food;
    }

    @Override
    public Food createNoodle() {
        System.out.println("准备制作牛肉面~");
        Food food = foodService.createNoodle();
        System.out.println("制作完成啦");
        return food;
    }
}

/**
 * 接口
 */
interface FoodService {
    /**
     * 鸡肉饭
     *
     * @return
     */
    Food createChicken();

    /**
     * 面条
     *
     * @return
     */
    Food createNoodle();
}

class Chicken extends Food {

    public Chicken(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "名字：" + getName() + " 辣椒：" + getSpicy() + " 盐：" + getSalt();
    }
}

class Noodle extends Food {
    public Noodle(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "名字：" + getName() + " 辣椒：" + getSpicy() + " 盐：" + getSalt();
    }

}

/**
 *
 */
class FoodServiceImpl implements FoodService {

    /**
     * 鸡肉饭
     *
     * @return
     */
    @Override
    public Food createChicken() {
        Food food = new Chicken("鸡肉饭");
        food.setSpicy("1g");
        food.setSalt("3g");
        return food;
    }

    /**
     * 面条
     *
     * @return
     */
    @Override
    public Food createNoodle() {
        Food food = new Noodle("牛肉面");
        food.setSpicy("5g");
        food.setSalt("5g");
        return food;
    }
}

/**
 * 客户端调用，注意，我们要用代理来实例化接口：
 */
class ProxyApp {
    public static void main(String[] args) {
        //创建代理对象
        FoodService serviceProxy = new FoodServiceProxy();
        //使用代理对象 制作鸡肉饭
        var chicken = serviceProxy.createChicken();
        System.out.println(chicken.toString());
        //使用代理对象 制作牛肉面
        var noodel = serviceProxy.createNoodle();
        System.out.println(noodel.toString());
    }
}



