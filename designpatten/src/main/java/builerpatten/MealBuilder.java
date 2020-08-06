package builerpatten;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/6 000614:04
 */
public class MealBuilder {

    private ColdDrink coldDrink;

    private Burger burger;

    public MealBuilder setColdDrink(ColdDrink coldDrink) {
        this.coldDrink = coldDrink;
        return this;
    }

    public MealBuilder setBurger(Burger burger) {
        this.burger = burger;
        return this;
    }

    protected MealBuilder(){
        super();
    }

    public static MealBuilder create(){
        return new MealBuilder();
    }

    public Meal build(){
        Meal meal = new Meal();
        meal.addItem(burger);
        meal.addItem(coldDrink);
        return meal;
    }

}
