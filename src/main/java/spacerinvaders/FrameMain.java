package spacerinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class FrameMain extends JFrame {
    public FrameMain() {
        super("SpaceInvaders");
        SpaceInvader.registerFont(FrameMain.class.getResource("Fonts/PixelGameFont.ttf"));
        setLayout(null);
        addKeyListener(pShipMovement);
        // playlist.add(bisbal);
        // playlist.add(ellaesMiNena);

        // playlist.get(contPlayList).play();

        lblBackgroundImage = new JLabel(new ImageIcon(FrameMain.class.getResource("images/galaxy.png")));
        lblBackgroundImage.setSize(lblBackgroundImage.getPreferredSize());
        lblBackgroundImage.setLocation(0, 0);
        setContentPane(lblBackgroundImage);

        writeFile(0, System.getProperty("user.home")+"/AppData/Roaming/spaceinvaders/scoreIngame.txt");
        score=readFile(System.getProperty("user.home")+"/AppData/Roaming/spaceinvaders/scoreIngame.txt");
        highScore=readFile(System.getProperty("user.home")+"/AppData/Roaming/spaceinvaders/highScore.txt");

        lblHighScore= new JLabel("HIGHSCORE: "+highScore);
        lblHighScore.setSize(200,30);
        lblHighScore.setLocation(230, 530);
        lblHighScore.setForeground(Color.white);
        lblHighScore.setFont(new Font("PixelGameFont",Font.ITALIC,16));
        add(lblHighScore);
        

        spaceShip = new SpaceShip();
        add(spaceShip);

        int x = 30, y = 10;
        for (int i = 0; i < aliensPlacement.length; i++) {
            for (int j = 0; j < aliensPlacement[i].length; j++) {
                alien = new Alien(x, y);
                aliens.add(alien);
                add(alien);
                if (j == 8) {
                    x = 30;
                    y += 70;
                } else {
                    x += 60;
                }

            }
        }

        int xBlock = 40, yBlock = 350;
        for (int i = 0; i < blockPlacement.length; i++) {
            Blocks block = new Blocks(xBlock, yBlock);
            blocks.add(block);
            add(block);
            xBlock += 210;
        }

        int xLifeNumber = 30, yLifeNumber = 530;
        for (int i = 0; i < spaceShip.gethp(); i++) {

            lblLifenumber = new JLabel(new ImageIcon(FrameMain.class.getResource("images/nave.png")));
            lblLifenumber.setSize(20, 20);
            lblLifenumber.setLocation(xLifeNumber, yLifeNumber);
            lifeNumber.add(lblLifenumber);
            add(lblLifenumber);

            xLifeNumber += 40;
        }

        lblScore = new JLabel("SCORE "+score);
        lblScore.setSize(200,30);
        lblScore.setLocation(480, 530);
        lblScore.setForeground(Color.white);
        lblScore.setFont(new Font("PixelGameFont",Font.ITALIC,16));
        add(lblScore);

        fpsBullet = new Timer(17, new BulletMovement());
        fpsSpaceShip = new Timer(17, pShipMovement);
        fpsAlien = new Timer(17, new AlienMovement());
        //fpsSound = new Timer(1000, new PlayList());

        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setVisible(true);
    }

    // private class PlayList implements ActionListener {

    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         contSeconds += 1000;
    //         System.err.println();
    //         if (contSeconds >= playlist.get(contPlayList).audioClip.getMicrosecondLength()) {
    //             if (contPlayList < playlist.size()) {

    //                 playlist.get(contPlayList).stop();
    //                 contPlayList++;
    //                 playlist.get(contPlayList).play();
    //             }
    //         }

    //     }

    // }

    private void writeFile(int score, String path){
        try (PrintWriter f = new PrintWriter(new FileWriter(path))) {
            f.println(score);
        } catch (IOException e1) {
            System.err.println("Error de acceso al archivo");
        }
    }

    private int readFile(String path){
        int points=0;
        try (Scanner sc = new Scanner(new File(path))) {
            points=Integer.parseInt(sc.nextLine());
        } catch (FileNotFoundException e) {
            System.err.println("no hay archivo");
        }catch(NoSuchElementException e ){
            System.err.println("No hay linea que leer");
        }catch (NumberFormatException e ){
            System.err.println("En el archivo no se puede leeer un entero");
        }
        return points;
    }

    private class SpaceShipMovement extends KeyAdapter implements ActionListener {

        @Override
        public void keyPressed(KeyEvent e) {
            fpsBullet.start();
            fpsAlien.start();
            //fpsSound.start();

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                directionX = true;
                fpsSpaceShip.start();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                directionX = false;
                fpsSpaceShip.start();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            fpsSpaceShip.stop();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (directionX) {
                if (spaceShip.getLocation().x < 525) {
                    spaceShip.setLocation((spaceShip.getLocation().x + 10), spaceShip.getLocation().y);
                }
            } else {
                if (spaceShip.getLocation().x > 0) {
                    spaceShip.setLocation((spaceShip.getLocation().x - 10), spaceShip.getLocation().y);
                }
            }
        }

        boolean directionX;
    }

    private class BulletMovement implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            contSecondsBullet++;
            int random = random();
            if (contSecondsBullet % 50 == 0) {
                bulletSpaceShip = spaceShip.shoot();
                bulletsSpaceShip.add(bulletSpaceShip);
                add(bulletSpaceShip);
                if (aliens.size() > 0) {
                    bulletAlien = aliens.get(random).shoot();
                } else {
                    writeFile(score,System.getProperty("user.home")+"/AppData/Roaming/spaceinvaders/scoreIngame.txt");
                    fpsBullet.stop();
                    new FrameMain();
                    dispose();
                    
                }
                bulletsAlien.add(bulletAlien);
                add(bulletAlien);

            }
            bulletMovement(false);
            bulletMovement(true);

        }

        public void bulletMovement(boolean flag) {
            if (flag) {
                bulletSpaceShip(bulletsSpaceShip);
            } else {
                bulletAlien(bulletsAlien);
            }
        }

        public void bulletSpaceShip(ArrayList<Bullet> bullet) {
            for (int i = 0; i < bullet.size(); i++) {
                bullet.get(i).setLocation(bullet.get(i).getX(), bullet.get(i).getLocation().y - 10);
                for (int j = 0; j < aliens.size(); j++) {
                    if (bullet.size() > 0) {

                        if (bullet.get(i).getY() < 25) {
                            bullet.get(i).setVisible(false);
                            bullet.remove(i);
                        }
                    }
                    intersectsBlock(bullet);
                    if (bullet.size() > 0) {

                        if (bullet.get(i).getBounds().intersects(aliens.get(j).getBounds())) {

                            alien.AlienDeadSound.volume(-50.0f);
                            alien.AlienDeadSound.play();
                            animationAlien(aliens.get(j));
                            score +=100;
                            lblScore.setText("SCORE "+score);
                            bullet.get(i).setVisible(false);

                            bullet.remove(i);
                        }

                    }
                }

            }
        }

        public void bulletAlien(ArrayList<Bullet> bullet) {

            for (int i = 0; i < bullet.size(); i++) {

                bullet.get(i).setLocation(bullet.get(i).getX(), bullet.get(i).getLocation().y + 15);
                for (int j = 0; j < aliens.size(); j++) {
                    if (bullet.size() > 0) {

                        if (bullet.get(i).getY() > 550) {
                            bullet.get(i).setVisible(false);
                            bullet.remove(i);
                        }
                    }
                    intersectsBlock(bullet);
                    if (bullet.size() > 0) {

                        if (bullet.get(i).getBounds().intersects(spaceShip.getBounds())) {

                            bullet.get(i).setVisible(false);
                            bullet.remove(i);
                            spaceShip.hit();
                            animationSpaceShip();
                            lifeNumber.get(spaceShip.gethp()).setVisible(false);
                            lifeNumber.remove(spaceShip.gethp());

                            if (spaceShip.hp < 1) {
                                writeFile(0,System.getProperty("user.home")+"/AppData/Roaming/spaceinvaders/scoreIngame.txt");
                                
                                if(score>readFile(System.getProperty("user.home")+"/AppData/Roaming/spaceinvaders/highScore.txt")){
                                    writeFile(score, System.getProperty("user.home")+"/AppData/Roaming/spaceinvaders/highScore.txt");

                                }
                                dispose();
                                GameOver gameOver = new GameOver(FrameMain.this);
                                gameOver.setSize(400, 200);
                                gameOver.setLocationRelativeTo(null);
                                gameOver.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                gameOver.setUndecorated(true);
                                gameOver.setVisible(true);
                            }
                        }
                    }
                }

            }
        }

        private void animationAlien(Alien alien) {
            new Thread(() -> {
                for (int i = 0; i < 2; i++) {
                    if (i % 2 == 0) {
                        alien.setIcon(null);
                    } else {
                        alien.setIcon(new ImageIcon(FrameMain.class.getResource("images/alien.png")));
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                }
                alien.setVisible(false);
                aliens.remove(alien);
            }) {
            }.start();
        }

        private void animationSpaceShip() {
            new Thread(() -> {
                for (int i = 0; i < 6; i++) {
                    if (i % 2 == 0) {
                        spaceShip.setIcon(null);
                    } else {
                        spaceShip.setIcon(new ImageIcon(FrameMain.class.getResource("images/nave.png")));
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                }
            }) {
            }.start();
        }

        private int random() {
            return (int) (Math.random() * (aliens.size() - 1));
        }

        public void intersectsBlock(ArrayList<Bullet> bullet) {
            for (int i = 0; i < bullet.size(); i++) {

                for (int j2 = 0; j2 < blocks.size(); j2++) {
                    if (bullet.size() > 0) {

                        if (bullet.get(i).getBounds().intersects(blocks.get(j2).getBounds())) {

                            bullet.get(i).setVisible(false);
                            bullet.remove(i);
                            blocks.get(j2).hit();
                            if (blocks.get(j2).getHP() < 1) {
                                blocks.get(j2).setVisible(false);
                                blocks.remove(j2);
                            }

                        }
                    }
                }
            }
        }
    }

    private class AlienMovement implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            contSecondsAlien++;

            if (contSecondsAlien % 40 == 0) {

                for (int i = 0; i < aliens.size(); i++) {
                    if (aliens.get(i).getLocation().x > 540) {
                        directionX = false;
                        directionY = true;
                    }
                    if (aliens.get(i).getLocation().x < 5) {
                        directionX = true;
                        directionY = true;
                    }
                }
                if (directionY) {
                    for (int j = 0; j < aliens.size(); j++) {

                        aliens.get(j).setLocation(aliens.get(j).getLocation().x, aliens.get(j).getY() + 30);
                        if (j <= aliens.size() - 1) {

                            directionY = false;
                        }
                    }
                }
                if (!directionY) {
                    for (int i = 0; i < aliens.size(); i++) {

                        if (directionX) {
                            aliens.get(i).setLocation(aliens.get(i).getLocation().x + 20, aliens.get(i).getY());
                        } else {
                            aliens.get(i).setLocation(aliens.get(i).getLocation().x - 20, aliens.get(i).getY());

                        }
                    }

                }

            }
        }

        boolean directionX = true;

        /**
         * When it is false , the Y doesnt move
         */
        boolean directionY = false;

    }

    SpaceShip spaceShip;
    Alien alien;
    Bullet bulletSpaceShip;
    Bullet bulletAlien;
    JLabel lblBackgroundImage;
    JLabel lblLifenumber;
    JLabel lblScore;
    JLabel lblHighScore;
    Timer fpsSpaceShip;
    Timer fpsBullet;
    Timer fpsAlien;
    Timer fpsSound;
    int contSecondsBullet;
    int contSecondsAlien;
    int contPlayList = 0;
    int contSeconds = 0;
    int score=0;
    int highScore=0;
    ArrayList<Bullet> bulletsSpaceShip = new ArrayList<>();
    ArrayList<Bullet> bulletsAlien = new ArrayList<>();
    ArrayList<Alien> aliens = new ArrayList<>();
    ArrayList<Blocks> blocks = new ArrayList<>();
    ArrayList<JLabel> lifeNumber = new ArrayList<>();
    SpaceShipMovement pShipMovement = new SpaceShipMovement();

    char[][] aliensPlacement = { { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
            { '.', '.', '.', '.', '.', '.', '.', '.', '.' }, };

    char[] blockPlacement = { '.', '.', '.' };

    /**
     * Variables para poner musica y un ArrayList para hacer una playlist
     */
    //SClip ellaesMiNena = new SClip(FrameMain.class.getResource("music/Ella es mi nena.wav"));
    SClip bisbal = new SClip(FrameMain.class.getResource("music/bisbal.wav"));
    SClip shootSound = new SClip(FrameMain.class.getResource("music/shoot.wav"));
    SClip explosionSound = new SClip(FrameMain.class.getResource("music/explosion.wav"));
    SClip AlienDeadSound = new SClip(FrameMain.class.getResource("music/invaderkilled.wav"));
    ArrayList<SClip> playlist = new ArrayList<>();
}
