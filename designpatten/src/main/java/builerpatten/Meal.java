package builerpatten;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fumj
 * @projectName example
 * @description: TODO
 * @date 2020/8/6 000614:00
 */
public class Meal {
    private List<Item> itemList = new ArrayList<>(16);

    public void addItem(Item item){
        itemList.add(item);
    }

    public float getCost(){
        float cost = 0.0f;
        for (Item item : itemList) {
            cost += item.price();
        }
        return cost;
    }


    public void showItems(){
        for (Item item : itemList) {
            System.out.print("Item : "+item.name());
            System.out.print(", Packing : "+item.packing().pack());
            System.out.println(", Price : "+item.price());
        }
    }
}
