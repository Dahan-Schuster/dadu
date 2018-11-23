package spaceGame;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	/** Pôe uma subimagem da imagem carregada pela classe BufferedIMageLoader na tela
	 * Este método foi feito para o formato específico da imagem que contém todos os sprites
	 * Ela se chama Sprites.png e está na pasta res na raiz do projeto
	 * A imagem possui dimensão de 256x256 px e um pattern que a divide em linhas e colunas
	 * que formam células de 32x32 pixels. Cada célula possui um sprite.
	 * 
	 * @param col a coluna do sprite requisitado
	 * @param row a linha do sprite requisitado
	 * @param width a largura do sprite ( precisa ser 32 para não ter conflito com os outros sprites da imagem)
	 * @param heigth a altura do sprite ( precisa ser 32 para não ter conflito com os outros sprites da imagem)
	 * @return o sprite requisitado :)
	 */
	public BufferedImage grabImage(int col, int row, int width, int heigth) {
		
		BufferedImage img = image.getSubimage((col*32)-32, (row*32)-32, width, heigth);
		
		return img;
	}
}
