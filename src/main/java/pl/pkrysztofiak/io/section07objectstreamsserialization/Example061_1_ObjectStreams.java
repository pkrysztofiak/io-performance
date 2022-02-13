package pl.pkrysztofiak.io.section07objectstreamsserialization;

import java.io.*;

public class Example061_1_ObjectStreams {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Player player = new Player();
        player.setName("John");
        player.setAge(17);

        String filename = "section07/example061/player.obj";
        serialize(player, filename);
        Player p = (Player) deserialize(filename);
        System.out.println(p);
    }

    private static void serialize(Player player, String filename) throws IOException {
        var out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(player);
        out.close();
    }

    private static Object deserialize(String filename) throws IOException, ClassNotFoundException {
        var in = new ObjectInputStream(new FileInputStream(filename));
        Object object = in.readObject();
        in.close();
        return object;
    }
}