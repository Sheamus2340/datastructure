package org.sheamus.datastructure.tree;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.sheamus.datastructure.tree.Node;
import org.sheamus.datastructure.tree.Tree;

/**
 * Tree Tester.
 *
 * @author <Authors name>
 * @since <pre>七月 4, 2018</pre>
 * @version 1.0
 */
public class TreeTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: insert(int key, int otherData)
     *
     */
    @Test
    public void testInsert() throws Exception {
        Tree tree = new Tree();
        tree.insert(12,2);
        tree.insert(1,2);
        tree.insert(2,2);
        tree.insert(14,2);
        tree.insert(3,2);
        tree.insert(9,2);
        tree.insert(23,2);
        Node node = tree.find(12);
        System.out.println(node.getKeyData() + "---" + node.getOtherData());

        tree.preTraversing();
        /*tree.display();*/
    }

    /**
     *
     * Method: delete(int key)
     *
     */
    @Test
    public void testDelete() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: change(int key, int newKey)
     *
     */
    @Test
    public void testChange() throws Exception {
//TODO: Test goes here...
    }

    /**
     *
     * Method: find(int key)
     *
     */
    @Test
    public void testFind() throws Exception {
//TODO: Test goes here...
    }


} 
