/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisiamall.GUI;

import java.io.IOException;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import tunisiamall.midlet.Midlet;


public class DrawStringCanv extends Canvas implements CommandListener {

    int w = getWidth();
    int h = getHeight();
    Image img;
    int x = w / 3;
    int y = h / 6;
    int k;
    int choix = 0;

    ListBoutiques listB = new ListBoutiques("Boutiques", List.IMPLICIT, Midlet.disp);
    ListEnseignes listE = new ListEnseignes("Enseignes", List.IMPLICIT, Midlet.disp);
    ListComptes listC = new ListComptes("Comptes", List.IMPLICIT, Midlet.disp);
    ListProduits listP = new ListProduits("Produits", List.IMPLICIT, Midlet.disp);
    ListPub listPu = new ListPub("Pub", List.IMPLICIT, Midlet.disp);
    Command cmBack = new Command("Back", Command.EXIT, 1);
    CarteF carte = new CarteF("Fidelite", Midlet.disp);
    Stats stats = new Stats("Stats", Midlet.disp);
    ListMap listMap = new ListMap("Map", List.IMPLICIT, Midlet.disp);

    public DrawStringCanv() {
   try {
            img = Image.createImage("/icon.png");
            addCommand(cmBack);
            setCommandListener(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected void paint(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, w, h);

        g.drawImage(img, w / 2, h / 2, Graphics.HCENTER | Graphics.VCENTER);
        g.setColor(0, 0, 0);
        g.drawString("Boutiques", w / 3, h / 8, Graphics.TOP | Graphics.LEFT);
        g.drawString("Statistiques", w / 10, h / 4, Graphics.TOP | Graphics.LEFT);
        g.drawString("Produits", w / 70, (int) (h * 0.45), Graphics.TOP | Graphics.LEFT);
        g.drawString("Packs publicitaires", w / 70, (int) (h * 0.65), Graphics.TOP | Graphics.LEFT);
        g.drawString("Enseignes", w / 3, (int) (h * 0.80), Graphics.TOP | Graphics.LEFT);
        g.drawString("Fidelite", (int) (w * 0.7), (int) (h * 0.65), Graphics.TOP | Graphics.LEFT);
        g.drawString("Comptes", (int) (w * 0.7), (int) (h * 0.45), Graphics.TOP | Graphics.LEFT);
        g.drawString("Maps", (int) (w * 0.7), (int) (h /4), Graphics.TOP | Graphics.LEFT);

        if (k == UP) {
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED, Font.FACE_SYSTEM));
            g.drawString("Boutiques", w / 3, h / 8, Graphics.TOP | Graphics.LEFT);
        }
        if (k == DOWN) {
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED, Font.FACE_SYSTEM));
            g.drawString("Enseignes", w / 3, (int) (h * 0.80), Graphics.TOP | Graphics.LEFT);
        }
        if (k == GAME_B) {
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED, Font.FACE_SYSTEM));
            g.drawString("Fidelite", (int) (w * 0.7), (int) (h * 0.65), Graphics.TOP | Graphics.LEFT);
        }
        if (k == RIGHT) {
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED, Font.FACE_SYSTEM));
            g.drawString("Comptes", (int) (w * 0.7), (int) (h * 0.45), Graphics.TOP | Graphics.LEFT);
        }
        if (k == LEFT) {
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED, Font.FACE_SYSTEM));
            g.drawString("Produits", w / 70, (int) (h * 0.45), Graphics.TOP | Graphics.LEFT);
        }
        if (k == GAME_A) {
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED, Font.FACE_SYSTEM));
            g.drawString("Packs publicitaires", w / 70, (int) (h * 0.65), Graphics.TOP | Graphics.LEFT);
        }
        if (k == GAME_C) {
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED, Font.FACE_SYSTEM));
            g.drawString("Statistiques", w / 10, h / 4, Graphics.TOP | Graphics.LEFT);
        }
        if (k == GAME_D) {
            g.setColor(0, 0, 0);
            g.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED, Font.FACE_SYSTEM));
            g.drawString("Maps", (int) (w * 0.7), (int) (h /4), Graphics.TOP | Graphics.LEFT);
        }

    }

    protected void keyPressed(int keyCode) {

        switch (getGameAction(keyCode)) {
            case UP:
                choix = 1;
                break;
            case DOWN:
                choix = 2;
                break;
            case RIGHT:
                choix = 3;
                break;
            case LEFT:
                choix = 4;
                break;
            case GAME_A:
                choix = 5;
                break;
            case GAME_B:
                choix = 6;
                break;
            case GAME_C:
                choix = 7;
                break;
            case GAME_D:
                choix = 8;
                break;
            case FIRE:
                if (choix == 1) {
                    Midlet.disp.setCurrent(listB);
                }
                if (choix == 2) {
                    Midlet.disp.setCurrent(listE);
                }
                if (choix == 3) {
                    Midlet.disp.setCurrent(listC);
                }
                if (choix == 4) {
                    Midlet.disp.setCurrent(listP);
                }
                if (choix == 5) {
                    Midlet.disp.setCurrent(listPu);
                }
                if (choix == 6) {
                    Midlet.disp.setCurrent(carte);
                }
                if (choix == 7) {
                    Midlet.disp.setCurrent(stats);
                }
                if (choix == 8) {
                    Midlet.disp.setCurrent(listMap);
                }
                break;
        }

        repaint();
        k = getGameAction(keyCode);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmBack) {

        }

    }

    public void destroyApp(boolean unconditional) {
    }

}
