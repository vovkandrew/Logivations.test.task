import algorythm.PackingTool;
import db.ProductDatabase;
import models.Case;
import models.Orderline;
import models.Package;
import models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.*;

public class Test {

    @Before
    public void clearProductDatabase() {
        ProductDatabase.clearDatabase();
    }

    @org.junit.Test
    public void packingToolCheckOverallOneBestOption() {
        Product p1 = new Product(1L, 2, 2, 2);
        Product p2 = new Product(2L, 6, 6, 6);
        Product p3 = new Product(3L, 7, 7, 7);
        List<Product> pl = Arrays.asList(p1, p2, p3);
        ProductDatabase.addProducts(pl);
        Case c1 = new Case(1L, 4, 4, 4);
        Case c2 = new Case(2L, 8, 8, 8);
        Case c3 = new Case(3L, 15, 15, 15);
        List<Case> cl = Arrays.asList(c1, c2, c3);
        Orderline o1 = new Orderline(1L, 8,  1L);
        Orderline o2 = new Orderline(2L, 250,  2L);
        Orderline o3 = new Orderline(3L, 150,  3L);
        List<Orderline> ol = Arrays.asList(o1, o2, o3);
        Package expected = new Package(1L, c1);
        expected.addSeveralProductsToPackage(8, p1);
        Package actual = new PackingTool().packOrder(cl, ol);
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void packingToolTwoBestOptionsWithTheSameVolumeFactorButWithDiffNumOfProducts() {
        Product p1 = new Product(1L, 2, 2, 2);
        Product p2 = new Product(2L, 2, 2, 2);
        Product p3 = new Product(3L, 7, 7, 7);
        List<Product> pl = Arrays.asList(p1, p2, p3);
        ProductDatabase.addProducts(pl);
        Case c1 = new Case(1L, 4, 4, 4);
        Case c2 = new Case(2L, 8, 8, 8);
        Case c3 = new Case(3L, 15, 15, 15);
        List<Case> cl = Arrays.asList(c1, c2, c3);
        Orderline o1 = new Orderline(1L, 8,  1L);
        Orderline o2 = new Orderline(2L, 64,  2L);
        Orderline o3 = new Orderline(3L, 150,  3L);
        List<Orderline> ol = Arrays.asList(o1, o2, o3);
        Package expected = new Package(2L, c2);
        expected.addSeveralProductsToPackage(64, p2);
        Package actual = new PackingTool().packOrder(cl, ol);
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void packingToolOneBestOptionOnlyOneProduct() {
        Product p1 = new Product(1L, 2, 2, 2);
        List<Product> pl = Arrays.asList(p1);
        ProductDatabase.addProducts(pl);
        Case c1 = new Case(1L, 4, 4, 4);
        Case c2 = new Case(2L, 8, 8, 8);
        Case c3 = new Case(3L, 15, 15, 15);
        List<Case> cl = Arrays.asList(c1, c2, c3);
        Orderline o1 = new Orderline(1L, 8,  1L);
        Orderline o2 = new Orderline(2L, 65,  1L);
        Orderline o3 = new Orderline(3L, 150,  1L);
        List<Orderline> ol = Arrays.asList(o1, o2, o3);
        Package expected = new Package(1L, c1);
        expected.addSeveralProductsToPackage(8, p1);
        Package actual = new PackingTool().packOrder(cl, ol);
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void packingToolOneBestOptionOnlyOneCase() {
        Product p1 = new Product(1L, 2, 2, 2);
        Product p2 = new Product(2L, 3, 3, 3);
        Product p3 = new Product(3L, 7, 7, 7);
        List<Product> pl = Arrays.asList(p1, p2, p3);
        ProductDatabase.addProducts(pl);
        Case c2 = new Case(2L, 8, 8, 8);
        List<Case> cl = Arrays.asList(c2);
        Orderline o1 = new Orderline(1L, 8,  1L);
        Orderline o2 = new Orderline(2L, 64,  2L);
        Orderline o3 = new Orderline(3L, 150,  3L);
        List<Orderline> ol = Arrays.asList(o1, o2, o3);
        Package expected = new Package(1L, c2);
        expected.addSeveralProductsToPackage(8, p1);
        Package actual = new PackingTool().packOrder(cl, ol);
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void packingToolWrongProductId() {
        Product p1 = new Product(1L, 2, 2, 2);
        Product p2 = new Product(2L, 3, 3, 3);
        Product p3 = new Product(3L, 7, 7, 7);
        Product p4 = new Product(10L, 2, 2, 2);
        List<Product> pl = Arrays.asList(p1, p2, p3);
        ProductDatabase.addProducts(pl);
        Case c2 = new Case(2L, 8, 8, 8);
        List<Case> cl = Arrays.asList(c2);
        Orderline o1 = new Orderline(1L, 8,  10L);
        Orderline o2 = new Orderline(2L, 64,  10L);
        Orderline o3 = new Orderline(3L, 150,  10L);
        List<Orderline> ol = Arrays.asList(o1, o2, o3);
        Package expected = new Package(2L, c2);
        expected.addSeveralProductsToPackage(64, p4);
        Package actual = new PackingTool().packOrder(cl, ol);
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void packingTollNoOptionsToPackAnyOrder() {
        Product p1 = new Product(1L, 2, 2, 2);
        Product p2 = new Product(2L, 6, 6, 6);
        Product p3 = new Product(3L, 7, 7, 7);
        List<Product> pl = Arrays.asList(p1, p2, p3);
        ProductDatabase.addProducts(pl);
        Case c1 = new Case(1L, 1, 1, 1);
        List<Case> cl = Arrays.asList(c1);
        Orderline o1 = new Orderline(1L, 8,  1L);
        Orderline o2 = new Orderline(2L, 250,  2L);
        Orderline o3 = new Orderline(3L, 150,  3L);
        List<Orderline> ol = Arrays.asList(o1, o2, o3);
        Package expected = new Package(1L, c1);
        expected.addSeveralProductsToPackage(8, p1);
        Package actual = new PackingTool().packOrder(cl, ol);
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void productCopyMethod() {
        Product expected = new Product(1L, 5, 5, 5);
        Product actual = expected.copy();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void caseCopyMethod() {
        Case expected = new Case(1L, 5, 5, 5);
        Case actual = expected.copy();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void boxRotateMethod() {
        Product product = new Product(1L, 10, 15, 5);
        Product expected = new Product(1L, 15, 5, 10);
        product.changePosition();
        Product actual = product.copy();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void productDatabaseAddProductCheckAndGetNumberOfAvailableProductsMethod() {
        Product product = new Product(1L, 10, 15, 5);
        ProductDatabase.addProduct(product);
        int expected = 1;
        int actual = ProductDatabase.getNumberOfAvailableProducts();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void productDatabaseAddProductsCheckAndGetNumberOfAvailableProductsMethod() {
        Product product = new Product(1L, 10, 15, 5);
        Product product2 = new Product(2L, 20, 15, 5);
        Product product3 = new Product(3L, 30, 15, 5);
        ProductDatabase.addProducts(Arrays.asList(product, product2, product3));
        int expected = 3;
        int actual = ProductDatabase.getNumberOfAvailableProducts();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void productDatabaseFindProductByIdMethod() {
        Product product = new Product(1L, 10, 15, 5);
        Product product2 = new Product(2L, 20, 15, 5);
        Product product3 = new Product(3L, 30, 15, 5);
        ProductDatabase.addProducts(Arrays.asList(product, product2, product3));
        ProductDatabase.addProducts(Arrays.asList(product, product2, product3));
        Product expected = product2.copy();
        Product actual = ProductDatabase.getProductById(2L);
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void packageAddOneProductToPackageMethod() {
        Product product = new Product(1L, 3, 3, 3);
        Case casee = new Case(1L, 15, 15, 15);
        Orderline order = new Orderline(1L, 1, 1L);
        Package pack = new Package(1L, casee);
        pack.addOneProductToPackage(product);
        int expected = 1;
        int actual = pack.getProducts().size();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void packageAddSeveralProductsToPackageMethod() {
        Product product = new Product(1L, 3, 3, 3);
        Case casee = new Case(1L, 15, 15, 15);
        Orderline order = new Orderline(1L, 1, 1L);
        Package pack = new Package(1L, casee);
        pack.addSeveralProductsToPackage(3, product);
        int expected = 3;
        int actual = pack.getProducts().size();
        Assert.assertEquals(expected, actual);
    }

    @org.junit.Test
    public void packageGetEffectiveVolumeFillingFactor() {
        Product product = new Product(1L, 3, 3, 3);
        Case casee = new Case(1L, 12, 12, 12);
        Orderline order = new Orderline(1L, 1, 1L);
        Package pack = new Package(1L, casee);
        pack.addSeveralProductsToPackage(64, product);
        double expected = 1;
        double actual = pack.getEffectiveVolumeFillingFactor();
        Assert.assertEquals(expected, actual, 0.00);
    }
}
