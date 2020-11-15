package com.example.foody.model;

import com.example.foody.R;

import java.util.ArrayList;

public class ListFood {
    private ArrayList<Food> listFood = new ArrayList();

    public ListFood() {

    }
    public ArrayList<Food> getListFood(){
        listFood.add(new Food("Bacon Pepperoni Pizza - Pizza thịt xông khói & xúc xích Đức",210000,R.drawable.food,0));
        listFood.add(new Food("Shrimp Onion Pizza - Pizza Tôm hải sản",180000,R.drawable.food,0));
        listFood.add(new Food("Korea Bulgogi BBQ Pizza - Pizza thịt xào kiểu Bulgogi Hàn Quốc",198000,R.drawable.food,0));
        listFood.add(new Food("Trà sữa cake cream",25000,R.drawable.food,1));
        listFood.add(new Food("Choco uji",25000,R.drawable.food,1));
        listFood.add(new Food("Hồng trà chanh+Hồng trà dứa",25000,R.drawable.food,1));
        listFood.add(new Food("Cơm gà xé",40000,R.drawable.food,2));
        listFood.add(new Food("Cơm sườn",30000,R.drawable.food,2));
        listFood.add(new Food("Cơm gà quay",50000,R.drawable.food,2));
        listFood.add(new Food("Cơm gà chiên mắm",30000,R.drawable.food,3));
        listFood.add(new Food("Cơm gà xé",25000,R.drawable.food,3));
        listFood.add(new Food("Cơm chiên dương châu",25000,R.drawable.food,3));
        listFood.add(new Food("Cháo xương tái",25000,R.drawable.food,4));
        listFood.add(new Food("Cháo xương",20000,R.drawable.food,4));
        listFood.add(new Food("Cháo gân",20000,R.drawable.food,4));
        listFood.add(new Food("ComBo Tokbokki thanh cua + Xúc xích chiên + Soda kiwi",65000,R.drawable.food,5));
        listFood.add(new Food("ComBo Kimbap truyền thống + Chả cá viên chiên + Tr",65000,R.drawable.food,5));
        listFood.add(new Food("ComBo Tokbokki gà cay + Nem chua rán + Dừa xiêm",65000,R.drawable.food,5));
        return listFood;
    }

    public ArrayList loadStoreFoods(ArrayList<Food> foods, int idStore){
        ArrayList list = new ArrayList();
        for (Food food : foods){
            if(food.getIdStore() == idStore) {
                list.add(food);
            }
        }
        return list;
    }
}
