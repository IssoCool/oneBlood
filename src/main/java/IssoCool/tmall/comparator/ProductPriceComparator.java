package IssoCool.tmall.comparator;

import java.util.Comparator;

import IssoCool.tmall.pojo.Product;

public class ProductPriceComparator implements Comparator<Product>{
	 
    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p1.getPromotePrice()-p2.getPromotePrice());
    }
 
}