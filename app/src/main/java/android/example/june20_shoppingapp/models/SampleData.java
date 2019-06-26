package android.example.june20_shoppingapp.models;

import java.util.ArrayList;

public class SampleData {

    public static ArrayList<Product> list;
    public static Product product;
    public static final String MOBILE = "mobile";
    public static final String LAPTOP = "laptop";
    public static final String DESKTOP = "desktop";

    public static ArrayList<Product> getData(String title) {
        switch (title) {
            case MOBILE:
                list = new ArrayList<>();
                product = new Product("Samsung", "2", 1000, "https://assets.mspimages.in/c/tr:w-375,h-300,c-at_max/15591-61-1.jpg");
                list.add(product);
                product = new Product("Iphone", "2", 1200, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone8/plus/iphone8-plus-gold-select-2018?wid=940&hei=1112&fmt=png-alpha&qlt=80&.v=1550795417455");
                list.add(product);
                product = new Product("Huawei", "2", 1500, "https://res.vmallres.com/pimages//product/6901443270958/428_428_1544769451481mp.png");
                list.add(product);
                return list;
            case LAPTOP:
                list = new ArrayList<>();
                product = new Product("Apple Laptop Air", "1", 2400, "https://www.bhphotovideo.com/images/images2500x2500/apple_z0rg_mjlt24_b_h_15_4_macbook_pro_laptop_1151725.jpg");
                list.add(product);
                product = new Product("Dell Laptop", "1", 2000, "https://assets.pcmag.com/media/images/330805-dell-inspiron-15-i15rv-6190-blk.jpg?width=1000&height=712");
                list.add(product);
                product = new Product("Window Laptop ", "1", 1800, "https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c05551586.png");
                list.add(product);
                return list;

            case DESKTOP:
                list = new ArrayList<>();
                product = new Product("Dell", "3", 10000, "https://i.ebayimg.com/images/g/Uy0AAOSw7GRZBSp0/s-l300.jpg");
                list.add(product);
                product = new Product("Dell2", "3", 20000, "https://images-na.ssl-images-amazon.com/images/I/61vr9AxtFOL._SX466_.jpg");
                list.add(product);
                product = new Product("Dell3", "3", 30000, "http://sc01.alicdn.com/kf/HTB1QsUfKeySBuNjy1zdq6xPxFXaL/Simple-aluminum-foldable-notebook-holder-detachable-laptop.jpg");
                list.add(product);
                return list;
            default:
                return null;
        }
    }

}
