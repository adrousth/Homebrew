import com.github.javafaker.Faker;
import entities.*;
import persistence.AssetDao;
import persistence.DataAccessObject;
import persistence.MemberDao;
import persistence.OrderDao;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Alex
 *         5/26/2016
 */
public class PopulateDatabase {
    private Faker faker;
    private MemberDao memberDao;
    private AssetDao assetDao;

    private void createMembers() {
        MemberRole role;

         memberDao = new MemberDao();

        // adding admin member
        Member member = new Member();
        member.setFirstName("Admin");
        member.setLastName("Member");
        member.setEmail("hello@world.net");
        member.setPassword("foobar");
        member.addRole(new MemberRole("MEMBER"));
        member.addRole(new MemberRole("ADMIN"));
        member.addRole(new MemberRole("HOP CZAR"));
        memberDao.addRecord(member);

        member = new Member();
        member.setFirstName("Normal");
        member.setLastName("Member");
        member.setEmail("example@example.com");
        member.setPassword("foobar");
        member.addRole(new MemberRole("MEMBER"));
        memberDao.addRecord(member);

        // adding 20 normal members
        for(int i = 0; i < 20; i++) {
            member = new Member();
            role = new MemberRole();
            String firstName = faker.name().firstName();
            member.setFirstName(firstName);
            String lastName = faker.name().lastName();
            member.setLastName(lastName);
            member.setEmail(faker.internet().emailAddress((firstName.substring(0,1) + lastName).toLowerCase()));
            member.setPhone(faker.phoneNumber().phoneNumber());
            member.setPassword("foobar" + (i + 1));
            role.setRole("MEMBER");
            member.addRole(role);
            memberDao.addRecord(member);
        }

    }

    private void createAssets() {
        assetDao = new AssetDao();
        Asset asset;
        for(int i = 0; i < 50; i++) {
            asset = new Asset();
            if (faker.bool().bool()) {
                asset.setType("HOP");
                asset.setName(faker.beer().hop());
            } else {
                asset.setType("GRAIN");
                asset.setName(faker.beer().malt());
            }
            asset.setDescription(faker.lorem().paragraph(2));
            asset.setCurrentStock((float) ((Math.round(2 * faker.number().numberBetween(40, 150) / (float) 10)) / 2.0));

            assetDao.addRecord(asset);
        }
    }

    private void createOrders() {
        memberDao = new MemberDao();
        Set<Member> members = memberDao.getNumberOfRecords(4,10);

        assetDao = new AssetDao();
        Set<Asset> hops = assetDao.searchNumberOfRecords(0,10,"type","HOP");
        Set<Asset> grains = assetDao.searchNumberOfRecords(0,10,"type","GRAIN");

        Order order;
        OrderDao orderDao = new OrderDao();
        for (Member member: members) {
            int numOrders = faker.number().numberBetween(3, 6);
            for (int i = 0; i < numOrders; i++) {


                if (faker.bool().bool()) {
                    order = createOrder(hops, "HOP");
                    order.setOrderStatus("unfilled");
                    order.setType("HOP");
                    order.setNotes(faker.lorem().paragraph());
                } else {
                    order = createOrder(grains, "GRAIN");
                    order.setOrderStatus("unfilled");
                    order.setType("GRAIN");
                    order.setNotes(faker.lorem().paragraph());
                }
                member.addMemberOrder(order);
                orderDao.createNewOrder(order);

            }
        }
    }

    public Order createOrder(Set<Asset> assets, String type) {
        Order order = new Order();

        int numItems = faker.number().numberBetween(1, 5);
        List<Asset> assetList = new ArrayList<>(assets);
        List<Float> quantities = getQuantities(numItems);
        numItems = quantities.size();
        Date dateOrdered = faker.date().past(360, TimeUnit.DAYS);
        Date dateCompleted = faker.date().future(15, TimeUnit.DAYS, dateOrdered);
        order.setCreatedAt(dateOrdered);
        order.setUpdatedAt(dateCompleted);
        for (int x = 0; x < numItems; x++) {

            OrderItem orderItem = new OrderItem();

            int num2 = faker.number().numberBetween(0, quantities.size());
            int num = faker.number().numberBetween(0, assetList.size());
            if (quantities.get(num2) != 0) {
                orderItem.setQuantity(quantities.get(num2));
                orderItem.setAssetId(assetList.get(num).getAssetId());
                orderItem.setType(type);
                order.addOrderItem(orderItem);

            }

            assetList.remove(num);
            quantities.remove(num2);

        }
        return order;
    }

    public List<Float> getQuantities(int numItems) {

        List<Float> quantities = new ArrayList<>();
        Faker faker = new Faker();

        float totalQuantity = (float) Math.round(2 * (Math.random() * (10 - 5) + 5)) / 2;
        float sum = 0;
        for (int i = 0; i < numItems; i++) {
            float next = (float) Math.random();
            quantities.add(next);
            sum += next;
        }
        double scale = 1d * totalQuantity / sum;
        sum = 0;
        for (int i = 0; i < numItems; i++) {
            float quantity = (float) Math.round((quantities.get(i) * scale) * 2) / 2;
            if (quantity != 0) {
                quantities.set(i, quantity);
                sum += quantities.get(i);
            } else {
                quantities.set(i, (float) (quantity + .5));
                sum += quantities.get(i);
            }
        }
        while(sum++ < totalQuantity) {
            int i = faker.number().numberBetween(0, numItems);
            float finalQuantity = quantities.get(i) + 1;
            quantities.set(i, (finalQuantity));
        }

        return quantities;
    }

    public static void main(String[] args) {
        PopulateDatabase pop = new PopulateDatabase();
        pop.run();
    }

    private void run() {
        faker = new Faker();
        createMembers();
        createAssets();
        createOrders();

    }
}
