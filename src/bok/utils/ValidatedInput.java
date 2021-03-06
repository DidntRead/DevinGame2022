package bok.utils;

import bok.engine.game2d.Move;

import javax.swing.*;

public class ValidatedInput {

    public static Move getValidatedBoardSize(){
        while (true){
            String s = (String) JOptionPane.showInputDialog(
                    null,
                    "Type M and N Board size in following format (<M> <N>) example: 10 10",
                    "Queens",
                    JOptionPane.PLAIN_MESSAGE);

            //if the window is closed the whole game is stopped
            if (s==null)
                System.exit(0);

            try{
                String[] num = s.split(" ");
                if (num.length != 2)
                    continue;
                int x = Integer.parseInt(num[0]);
                int y = Integer.parseInt(num[1]);
                if (x < 3 || y < 3 || x > 50 || y > 50){
                    JOptionPane.showMessageDialog(null,"M and N should be between 3 and 50", "Invalid Board Size!",JOptionPane.ERROR_MESSAGE);
                    continue;
                }
                return new Move(x,y);
            }catch (Exception ignored){}
        }
    }
}
