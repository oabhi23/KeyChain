import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlockTest {

    List<Block> blockchain = new ArrayList<>();
    int prefix = 4;
    String prefixString = new String(new char[prefix]).replace('\0', '0');

    @Test
    public void givenBlockchain_whenNewBlockAdded_thenSuccess() throws Exception {
        Block newBlock = new Block(
                "The is a New Block.",
                "0d65d9aea14ac119173995894fee7a3d",
                new Date().getTime());
        newBlock.mineBlock(prefix);
        Assert.assertTrue(newBlock.getHash().substring(0, prefix).equals(prefixString));
        blockchain.add(newBlock);

        Block newBlock2 = new Block(
                "The is the Second Block.",
                blockchain.get(blockchain.size() - 1).getPreviousHash(),
                new Date().getTime());
        newBlock2.mineBlock(prefix);
        Assert.assertTrue(newBlock2.getHash().substring(0, prefix).equals(prefixString));
        blockchain.add(newBlock2);
    }

    @Test
    public void givenBlockchain_whenValidated_thenSuccess() {
        boolean flag = true;
        for (int i = 0; i < blockchain.size(); i++) {
            String previousHash = i==0 ? "0" : blockchain.get(i - 1).getHash();
            flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash())
                    && previousHash.equals(blockchain.get(i).getPreviousHash())
                    && blockchain.get(i).getHash().substring(0, prefix).equals(prefixString);
            if (!flag) break;
        }
        Assert.assertTrue(flag);
    }

    @Test
    public void givenBlockchain_whenTokenized_thenSuccess() throws Exception {
        Block newBlock = new Block(
                "password123",
                "0d65d9aea14ac119173995894fee7a3d",
                new Date().getTime());
        newBlock.mineBlock(prefix);
        Assert.assertTrue(newBlock.getHash().substring(0, prefix).equals(prefixString));
        blockchain.add(newBlock);

        Assert.assertNotEquals(blockchain.get(0).getData(), "password123");
        Assert.assertEquals("password123", TokenizeUtils.detokenizeData(blockchain.get(0).getData()));
    }
}
