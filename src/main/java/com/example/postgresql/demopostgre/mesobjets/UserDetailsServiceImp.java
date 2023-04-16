package com.example.postgresql.demopostgre.mesobjets;

import com.example.postgresql.demopostgre.beans.Journal;
import com.example.postgresql.demopostgre.beans.Utilisateur;
import com.example.postgresql.demopostgre.depots.JournalRepository;
import com.example.postgresql.demopostgre.depots.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    JournalRepository journalRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // Try to get the PASSWORD :
        String password = request.getParameter("password"); //
        // Get the credentials :
        //Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(username);
        //
        Utilisateur utilisateur =
                utilisateurRepository.findByIdentifiantAndPasswordAndActif(
                        username, password, 1);

        //Utilisateur utilisateur = utilisateurRepository.findByNom(username);
        User.UserBuilder builder = null;
        if (utilisateur != null) {

            // Envoyer un mail :


            builder =
                    org.springframework.security.core.
                            userdetails.User.withUsername(username);
            builder.password(utilisateur.getPassword());
            builder.roles(utilisateur.getRoles());

            // Save in the journal
            Journal jrl = new Journal();
            jrl.setIdusr(utilisateur.getIdusr());
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            try {
                jrl.setDates(new SimpleDateFormat("yyyy-MM-dd").
                        parse(date));
                jrl.setHeure(heure);
            }
            catch (Exception exc){

            }
            jrl.setAction("Utilisateur connecté");
            journalRepository.save(jrl);

        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}
