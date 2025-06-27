package control;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import model.PhotoDAO;

import java.io.InputStream;

@WebListener
public class ImageLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        for (int id = 1; id <= 25; id++) {
            String path = "/resources/images/prodotto" + id + ".jpg";

            try (InputStream imgStream = sce.getServletContext().getResourceAsStream(path)) {
                if (imgStream != null) {
                    PhotoDAO.updatePhoto(id, imgStream);
                    System.out.println("Immagine caricata per prodotto ID: " + id);
                } else {
                    System.out.println("Immagine non trovata per ID: " + id + " in " + path);
                }
            } catch (Exception e) {
                System.out.println("Errore nel caricamento immagine ID " + id + ": " + e.getMessage());
            }
        }
    }
}
