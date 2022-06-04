package bok.utils;

import bok.engine.game2d.Move;

import javax.swing.*;

public class ValidatedInput {

    public static Move getValidatedBoardSize(){
        while (true){
            String s = (String) JOptionPane.showInputDialog(
                    null,
                    "Type M and N Board size in following format (<M> <N>) example: 10 10",
                    "Isola",
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
                return new Move(x,y);
            }catch (Exception ignored){}
        }
    }
}
