package objetos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	private BufferedImage image;
	
	/**Retorna uma imagem no disco rígido do computador
	 * 
	 * @param path o diretório
	 * @return a imagem encontrada
	 * @throws IOException caso o diretório ou a imagem não existam
	 */
	public BufferedImage loadImage(String path)  {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException | IllegalArgumentException io) {
			image = new BufferedImage(450,  400, BufferedImage.TYPE_BYTE_GRAY);
		}
		return image;
	}
}
