package algorythm;

import db.ProductDatabase;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import models.Case;
import models.Orderline;
import models.Package;
import models.Product;

public class PackingTool {

    public Package packOrder(List<Case> cases, List<Orderline> orders) {
        List<Package> packages = new ArrayList<>();
        for (Case c: cases) {
            Case caseToCompare = c.copy();
            caseToCompare.changePosition();
            for (Orderline o: orders) {
                Product p = ProductDatabase.getProductById(o.getProductId());
                Product productToCompare = p.copy();
                productToCompare.changePosition();
                if (checkIfAllProductsFromOneOrderCanFitThisCase(
                        caseToCompare, productToCompare, o.getNumberOfProducts())
                && checkIfAnyAnyProductCanFitAnyCase(caseToCompare, productToCompare)) {
                    int remainingNumOfProd = o.getNumberOfProducts();
                    remainingNumOfProd -=
                            calculateEvenNumOfProdThatCanFitTheCaseWithoutRotation(
                                    caseToCompare, productToCompare);
                    Case subCaseOne = calculateFirstSubSide(caseToCompare, productToCompare);
                    remainingNumOfProd -=
                            calculateEvenNumOfProdThatCanFitTheCaseWithoutRotation(
                                    subCaseOne, productToCompare);
                    Case subCaseTwo = calculateSecSubSide(caseToCompare, productToCompare);
                    remainingNumOfProd -=
                            calculateEvenNumOfProdThatCanFitTheCaseWithoutRotation(
                                    subCaseTwo, productToCompare);
                    Case subCaseThree = calculateFirstSubSide(subCaseTwo, productToCompare);
                    remainingNumOfProd -=
                            calculateEvenNumOfProdThatCanFitTheCaseWithoutRotation(subCaseThree, productToCompare);
                    if (remainingNumOfProd <= 0) {
                        Package pack = new Package(o.getId(), c);
                        for (int i = 0; i < o.getNumberOfProducts(); i++) {
                            pack.addOneProductToPackage(p);
                        }
                        packages.add(pack);
                    }
                }
            }
        }
        Comparator<Package> byVolumeFactor = Comparator.comparing(Package::getEffectiveVolumeFillingFactor);
        Comparator<Package> byNumOfProdInPackage = Comparator.comparing(p -> p.getProducts().size());
        return packages.stream()
                .max(byVolumeFactor.thenComparing(byNumOfProdInPackage))
                .orElseThrow(() -> new NullPointerException(
                                "None of the cases available can accommodate orders provided to pack"));
    }

    private Case calculateFirstSubSide(Case casee, Product product) {
        int x = casee.getSizeX() / product.getSizeX() * product.getSizeX();
        int y = casee.getSizeY();
        int z = casee.getSizeZ() % product.getSizeZ();
        Case c = new Case(x, y, z);
        c.changePosition();
        return c;
    }

    private Case calculateSecSubSide(Case casee, Product product) {
        int x = casee.getSizeX() % product.getSizeX();
        int y = casee.getSizeY();
        int z = casee.getSizeZ();
        Case c = new Case(x, y, z);
        c.changePosition();
        return c;
    }

    private int calculateEvenNumOfProdThatCanFitTheCaseWithoutRotation(Case casee, Product product) {
        return ((casee.getSizeX()/product.getSizeX())
                * (casee.getSizeY()/product.getSizeY())
                * (casee.getSizeZ()/product.getSizeZ()));
    }

    private boolean checkIfAllProductsFromOneOrderCanFitThisCase(Case caseForPacking, Product product, int numOfProd) {
        return caseForPacking.getVolume() >= numOfProd * product.getVolume();
    }

    private boolean checkIfAnyAnyProductCanFitAnyCase(Case caseForPacking, Product product) {
        return (caseForPacking.getSizeX() >= product.getSizeX()
                && caseForPacking.getSizeY() >= product.getSizeY()
                && caseForPacking.getSizeZ() >= product.getSizeZ());
    }
}
