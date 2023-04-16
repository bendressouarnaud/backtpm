package com.example.postgresql.demopostgre.mesobjets;

import com.example.postgresql.demopostgre.beans.*;
import com.example.postgresql.demopostgre.depots.CommandeRepository;
import com.example.postgresql.demopostgre.depots.PanneauRepository;
import com.example.postgresql.demopostgre.depots.TailleRepository;
import com.example.postgresql.demopostgre.depots.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserProcess {

    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    PanneauRepository panneauRepository;
    @Autowired
    TailleRepository tailleRepository;
    @Autowired
    CommandeRepository commandeRepository;

    @PersistenceUnit
    EntityManagerFactory emf;


    public void computePrix(String date_s) {
        try {
            // First get SUPPORT types :
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            //Date dateS = new SimpleDateFormat("yyyy-MM-dd").parse(date_s);

            System.out.println("date_s :  "+date_s);
            //System.out.println("dateS :  "+dateS);

            List<Panneau> listeDesPanneaux =
                    panneauRepository.findAll();//.findAllByDatecreation(date_s);
            System.out.println("taille listeDesPanneaux  :  "+listeDesPanneaux.size());
            Date tpdatedebut = new SimpleDateFormat("yyyy-MM-dd").
                    parse("2019-01-01");
            Date tpdatefin = new SimpleDateFormat("yyyy-MM-dd").
                    parse("2019-12-31");
            String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
            String datej = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // Browse :
            //Commande cmde;
            int i = 0;
            for (Panneau pn : listeDesPanneaux) {
                i++;
                System.out.println("i = " + i + "  /  " + listeDesPanneaux.size());

                Commande cmde = new Commande();
                cmde.setDatedebut(tpdatedebut);
                cmde.setDatefin(tpdatefin);
                cmde.setDates(new SimpleDateFormat("yyyy-MM-dd").parse(datej));
                cmde.setHeure(heure);
                cmde.setPdtpublier("--");

                EntityManager emr = emf.createEntityManager();
                emr.getTransaction().begin();
                Object population = emr.createQuery(
                        "select c.population " +
                                "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                                "join ville c on b.idvill=c.idvil " +
                                "where idsec=" + pn.getIdsec()).getSingleResult();

                int populat = (Integer) population;
                emr.getTransaction().commit();
                emr.close();

                //
                // Compute the COST
                Taille taille = tailleRepository.findByIdtai(pn.getTaille());
                CalculTsp cTsp = new CalculTsp(populat, pn.getTypes());
                double cout = cTsp.calculMontantTsp(tpdatedebut, tpdatefin, taille.getValeur());
                cmde.setCout((int) cout);

                // Calculer L'ODP si necessaire :
                emr = emf.createEntityManager();
                emr.getTransaction().begin();
                Object taxeODP = emr.createQuery(
                        "select c.taux " +
                                "from secteur a inner join quartier b on a.idquar=b.idqua inner " +
                                "join ville c on b.idvill=c.idvil " +
                                "where idsec=" + pn.getIdsec()).getSingleResult();

                int taux = (Integer) taxeODP;
                emr.getTransaction().commit();
                emr.close();
                //    * * * * * * * * *   Superficie ODP
                taille = tailleRepository.findByIdtai(pn.getSuperficie());
                double coutODP = cTsp.calculMontantOdp(tpdatedebut, tpdatefin, taille.getValeur(), taux);
                //System.out.println("Cout ODP : "+coutODP);
                cmde.setCoutodp((int) coutODP);

                cmde.setIdpan(pn.getIdpan());
                // Client :
                cmde.setIdcli(1);
                // Save :
                commandeRepository.save(cmde);
            }
        }
        catch (Exception exc){
            System.out.println("Exception exc :  "+exc.getLocalizedMessage());
        }
    }


    // Get Back the role of the user
    /*public String getBackRole(Principal principal){
        String retour="utilisateur";
        try {
            Utilisateur utilisateur =
                    utilisateurRepository.findByIdentifiant(
                            principal.getName().trim());
            retour=utilisateur.getRoles();
        }
        catch (Exception exc){
        }

        //
        return retour;
    }
    */

}
