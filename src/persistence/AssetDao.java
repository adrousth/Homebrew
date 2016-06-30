package persistence;

import entities.Asset;

/**
 * @author Alex
 *         6/26/2016
 */
public class AssetDao extends DataAccessObject<Asset> {

    public AssetDao() {
        super(Asset.class);
    }


}
