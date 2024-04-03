import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener{
    int frameWidth = 360;
    int frameHeight = 640;
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    int playerStartPosX = 0;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 34;
    int pipeHeight = frameHeight/2;
    // setting posisi pipe dan ukurannya


    Player player;
    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    public ArrayList<Pipe> pipes;

    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        setBackground(Color.blue);

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        pipesCooldown.start();
        controllerGame();
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }
    }

    public void move() {
        // Menambahkan kondisi baru tujuannya untuk menghindari saling ganti nilai velocityY antara gravity dan jarak loncat
        if(player.jump == false){
            // jika player tidak sedang loncat
            player.setVelocityY(gravity);
            // nilai velocity di ubah menjadi nilai gravity
        }else{
            // jika player sedang loncat
            player.jump = false;
            // maka nilai player saat loncat(true) di ubah menjadi tidak sedang loncat (false)
        }
        player.setPosY(player.getPosY() + player.getVelocityY());
        // Set nilai posY
        // player.setPosY(Math.max(player.getPosY(), 0));
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        repaint();
        move();
    }

    public void controllerGame(){
        // Membuat controller untuk game
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // Ketika menekan space
                    player.setVelocityY(-30);
                    // velocityY di ubah ke -30(Disesuaikan) tujuan nya untuk membuat player ke atas
                    player.jump = true;
                    // untuk mengecheck player jika sedang loncat
                }
            }
        });
    }


    public void placePipes() {
        // Perbaikan untuk pipa agar tidak ada pipa yang patah di posisi atas
        int randomPosy = (int) (pipeStartPosY + pipeHeight/4 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/6;
        // karena pipa atas di tarik ke atas maka openingSpace menjadi lebih besar maka pembagian diubah menjadi 6 untuk mengecilkan ukurannya
        Pipe upperPipe = new Pipe(pipeStartPosX, (randomPosy - pipeHeight/4) , pipeWidth, pipeHeight, upperPipeImage);
        // randomPosY dikurangi tinggi pipa / 4 tujuan nya menutup semua bagian atas pipa
        pipes.add(upperPipe);
        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosy + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

}