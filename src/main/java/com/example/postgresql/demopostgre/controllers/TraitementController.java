package com.example.postgresql.demopostgre.controllers;

import com.example.postgresql.demopostgre.beans.*;
import com.example.postgresql.demopostgre.depots.*;
import com.example.postgresql.demopostgre.mesobjets.*;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TraitementController {

    @Autowired
    PanneauRepository panneauRepository;
    @Autowired
    PanneauhistoRepository panneauhistoRepository;
    @Autowired
    VilleRepository villeRepository;
    @Autowired
    QuartierRepository quartierRepository;
    @Autowired
    SecteurRepository secteurRepository;
    @Autowired
    JournalRepository journalRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Autowired
    TypesRepository typesRepository;
    @Autowired
    ParametresRepository parametresRepository;

    @PersistenceUnit
    EntityManagerFactory emf;
    @Value("${adresselocal}")
    private String lienapp;

    // Static
    public static List<UserPosition> listeUserPosition =
            new ArrayList<UserPosition>();


    // M e t h o d s :

    @GetMapping(value="/affichertspanneaux")
    public String affichertspanneaux(Model model){
        model.addAttribute("lienapp", lienapp);
        return "touslespanneaux";
    }


    // Use getsupports
    @GetMapping(value="/getsupports" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<LatitudeLongitude> getsupports(){

        List<Panneau> listePanneau = panneauRepository.findAll();
        List<LatitudeLongitude> listeObjet = new ArrayList<LatitudeLongitude>();
        // Browse listePanneau :
        for(Panneau pn : listePanneau){
            // Create a new Object :
            LatitudeLongitude ll = new LatitudeLongitude(pn.getLatitude(),pn.getLongitude());
            listeObjet.add(ll);
        }
        return listeObjet;
    }


    @PostMapping(value="/gethistosupports")
    @ResponseBody
    public List<Panneauhisto> gethistosupports(){
        List<Panneauhisto> listePanneau = panneauhistoRepository.findAll();
        return listePanneau;
    }


    // Nouveau client
    @GetMapping(value="/choixafficherpanneaux")
    public String interfacetspanneaux(Model model){
        //
        List<Ville> listCommune = villeRepository.findAllByOrderByLibelleAsc();
        List<Types> listTypes = typesRepository.findAllByOrderByLibelleAsc();
        model.addAttribute("listCommune",listCommune);
        model.addAttribute("listTypes",listTypes);
        // Set app link :
        model.addAttribute("lienapp", lienapp);
        return "choixafficherpanneaux";
    }


    //
    @CrossOrigin("*")
    @GetMapping(value="/getitemback/{choix}/{id}" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<ItemBack> getitemback(@PathVariable int choix,
                                      @PathVariable int id){

        List<ItemBack> listeItem = new ArrayList<ItemBack>();

        switch (choix){
            case 0:
                // Commune a ete changée , charger les QUARTIERS afferants :
                List<Quartier> listeQuartier = quartierRepository.findAllByIdvill(id);
                for(Quartier qtr : listeQuartier){
                    listeItem.add(new ItemBack(qtr.getIdqua(), qtr.getLibelle()));
                }
                break;

            case 1:
                // Quartier a ete changé, changer les SECTEURS
                List<Secteur> listeSecteur = secteurRepository.findAllByIdquar(id);
                for(Secteur str : listeSecteur){
                    listeItem.add(new ItemBack(str.getIdsec(), str.getLibelle()));
                }
                break;

            case 2:
                // Secteur a ete changé :
                List<Panneau> listePanneau = panneauRepository.findAllByIdsec(id);
                for(Panneau pnu : listePanneau){
                    listeItem.add(new ItemBack(pnu.getIdpan(), pnu.getLibelle()));
                }
                break;


            case 3:
                // Get the different kind of support
                List<Types> listeTypes = typesRepository.findAll();
                for(Types typs : listeTypes){
                    listeItem.add(new ItemBack(typs.getIdtyp(), typs.getLibelle()));
                }
                break;


            default:
                break;
        }

        return listeItem;
    }


    @CrossOrigin("*")
    @GetMapping(value="/getagentusers" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<RetourUtilisateur> getagentusers(@RequestParam("idpan") int idpan){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> listeAgents = new ArrayList<Object[]>();
        List <Object[]> listeAdmins = new ArrayList<Object[]>();
        List <RetourUtilisateur> ListeRetourUtilisateur = new ArrayList<RetourUtilisateur>();

        //
        listeAgents = emr.createQuery(
            "select distinct e.idusr as ids,e.nom as nm from panneau a inner join " +
            "utilisateur e on a.idusr=e.idusr where a.idsec = ( " +
            "select a.idsec from panneau a where a.idpan="+idpan+")").
            getResultList();
        listeAdmins = emr.createQuery(
                "select distinct h.idusr as ids,h.nom as nm from utilisateur h " +
                        "where h.roles ='admin'").
                getResultList();

        emr.getTransaction().commit();
        emr.close();
        for(Object[] objet : listeAdmins){
            listeAgents.add(objet);
        }

        // Now Process :
        RetourUtilisateur ru;
        for(Object[] objet : listeAgents){
            //
            ru = new RetourUtilisateur();
            ru.setIdusr(Integer.parseInt(String.valueOf(objet[0])));
            ru.setNom(String.valueOf(objet[1]));
            ListeRetourUtilisateur.add(ru);
        }
        return ListeRetourUtilisateur;
    }



    @CrossOrigin("*")
    @GetMapping(value="/getsupportbyid" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<InfoImage> getsupportbyid(@RequestParam("idpan") int idpan){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> listeResultats = new ArrayList<Object[]>();

        //
        listeResultats = emr.createQuery(
            "select a.image,a.emplacement, a.dateheure,  b.libelle as secteurs," +
                    "c.libelle as quartiers, d.libelle as villes, " +
                    "e.nom as users, a.datecreation from panneau a inner join " +
                    "secteur b on a.idsec = b.idsec inner join " +
                    "quartier c on b.idquar=c.idqua inner join " +
                    "ville d on c.idvill=d.idvil inner join " +
                    "utilisateur e on a.idusr=e.idusr " +
                    "where a.idpan= "+idpan).getResultList();
        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<InfoImage> listeInfoImage = new ArrayList<InfoImage>();
        // Browse listePanneau :
        for(Object[] ob : listeResultats){
            // Create a new Object :
            String dates = String.valueOf(ob[7]).replaceAll(" 00:00:00.0","");;
            InfoImage ii = new InfoImage((String)ob[0], (String)ob[1], dates,
                    (String)ob[3], (String)ob[4], (String)ob[5], (String)ob[6]);
            listeInfoImage.add(ii);
        }

        emr.close();
        return listeInfoImage;
    }




    // Use getsupports
    // @GetMapping(value="/getsupportschoix/{choix}/{id}" , produces = { "application/xml", "text/xml" },
    @CrossOrigin("*")
    @GetMapping(value="/getsupportschoix" , produces = { "application/xml", "text/xml" },
            consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<LatitudeLongitude> getsupportschoix(@RequestParam("choix") int choix,
                                                    @RequestParam("typesupport") int typesupport,
                                                    @RequestParam("id") String id){

        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> histoCommande = new ArrayList<Object[]>();

        // for TYPES :
        String filtre = (typesupport > 0) ? (" and a.types="+typesupport) : "";

        switch (choix){
            case 0:
                // Commune :
                histoCommande = emr.createQuery(
                        "select a.latitude,a.longitude,a.libelle,e.libelle,a.idpan " +
                                "from panneau a inner join secteur b on " +
                                "a.idsec=b.idsec inner join quartier c on b.idquar=c.idqua inner join " +
                                "ville d on c.idvill=d.idvil " +
                                "inner join types e on a.types=e.idtyp "+
                                "where d.idvil in "+id+filtre).getResultList();
                break;

            case 1:
                // Quartier :
                histoCommande = emr.createQuery(
                        "select a.latitude,a.longitude,a.libelle,d.libelle,a.idpan " +
                                "from panneau a inner join secteur b on " +
                                "a.idsec=b.idsec inner join quartier c on b.idquar=c.idqua " +
                                " inner join types d on a.types=d.idtyp  " +
                                "where c.idqua in "+id+filtre).getResultList();
                break;

            case 2:
                // Secteur :
                histoCommande = emr.createQuery(
                        "select a.latitude,a.longitude,a.libelle,c.libelle,a.idpan " +
                                "from panneau a inner join secteur b on " +
                                "a.idsec=b.idsec inner join types c on a.types=c.idtyp" +
                                "  where b.idsec in "+id+filtre).getResultList();
                break;
        }
        emr.getTransaction().commit();

        // The list of OBJECTS :
        List<LatitudeLongitude> listeObjet = new ArrayList<LatitudeLongitude>();
        // Browse listePanneau :
        for(Object[] ob : histoCommande){
            // Create a new Object :
            LatitudeLongitude ll = new LatitudeLongitude((Double)ob[0],
                    (Double)ob[1], (String)ob[2], (String)ob[3], (Integer)ob[4]);
            listeObjet.add(ll);
        }
        //
        emr.close();
        return listeObjet;
    }





    // POST :
    @PostMapping(value="/soumettrechoix")
    public String soumettrechoix(@RequestParam("commune") int commune,
                                 @RequestParam("quartier") int quartier,
                                 @RequestParam("communemul") String communemul,
                                 @RequestParam("secteur") int secteur,
                                 @RequestParam("choix") int choix,
                                 @RequestParam("typesupport") int typesupport,
                                 @RequestParam("affichage") int affichage,
                                 Model model, Principal principal){

        String tampon2 = "";

        // traiter :
        switch (choix){
            case 0:
                // Afficher les points liés à une commune :
                tampon2 = "(" +commune+ ")";
                model.addAttribute("valeur", tampon2);
                Ville ville = villeRepository.findByIdvil(commune);
                model.addAttribute("information",
                        "Commune : "+ville.getLibelle());
                break;

            case 1:
                // Afficher les points liés à une quartier :
                tampon2 = "(" +quartier+ ")";
                model.addAttribute("valeur", tampon2);
                Quartier quart = quartierRepository.findByIdqua(quartier);
                model.addAttribute("information",
                        "Quartier : "+quart.getLibelle());
                break;

            case 2:
                // Afficher les points liés à un secteur :
                tampon2 = "(" +secteur+ ")";
                model.addAttribute("valeur",tampon2);
                Secteur sect = secteurRepository.findByIdsec(secteur);
                model.addAttribute("information",
                        "Secteur : "+sect.getLibelle());
                break;
        }

        //
        model.addAttribute("choix",choix);
        model.addAttribute("typesupport",typesupport);
        // Set app link :
        model.addAttribute("lienapp", lienapp);

        // Retour :
        String retour = (affichage >0) ? "touslespanneauxmaps" : "carte";

        if(affichage >0){
            // Track the usage of GOOGLE MAPS :
            Utilisateur utilisateur = utilisateurRepository.findByIdentifiant(principal.getName().trim());
            saveJournal(utilisateur.getIdusr(),
                    "L'utilisateur "+utilisateur.getNom()+" a utilisé GOOGLE MAPS !");
        }

        String tampon1 = "";
        if(affichage == 2) {
            tampon1 = "(" + communemul + ")";
            model.addAttribute("valeur", tampon1);
        }

        if(affichage >0){
            // Selectionner tous les utilisateurs
            List<Utilisateur> listeUtilisateur = utilisateurRepository.findAll();
            model.addAttribute("listeUtilisateur",listeUtilisateur);
        }

        return retour;
    }


    @GetMapping(value="/journal")
    public String interfacejournal(Model model){
        //
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> histoUtilisateur = emr.createQuery(
                "select distinct a.idusr,a.nom " +
                        "from utilisateur a inner join journal b on a.idusr=b.idusr").getResultList();
        model.addAttribute("histoJournal",histoUtilisateur);
        emr.getTransaction().commit();
        emr.close();

        List<Ville> listeCommune = villeRepository.findAll();
        model.addAttribute("listeCommune",listeCommune);
        //
        List<Types> listeTypes = typesRepository.findAll();
        model.addAttribute("listeTypes",listeTypes);
        return "journal";
    }



    // POST :
    @PostMapping(value="/soumettrejournal")
    public String soumettrejournal(
            @RequestParam("utilisateur") int utilisateur,
            @RequestParam("debut") String debut,
            @RequestParam("fin") String fin,
            @RequestParam("choix") int choix,
            @RequestParam("generer") int generer,
            @RequestParam("commune") int commune,
            @RequestParam("typesupport") int typesupport,
            Model model) throws Exception {

        // Get the dates :
        Date dateDBT = (choix!=5) ? new SimpleDateFormat("yyyy-MM-dd").
                parse(ClassFonction.retourDate(debut)) : null;
        Date dateFIN = (choix!=5) ? new SimpleDateFormat("yyyy-MM-dd").
                parse(ClassFonction.retourDate(fin)) : null;

        if(generer==1) {  // Soumettre :

            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();
            List<Object[]> histoUtilisateur = emr.createQuery(
                    "select distinct a.idusr,a.nom " +
                            "from utilisateur a inner join journal b on a.idusr=b.idusr").getResultList();
            emr.getTransaction().commit();
            emr.close();

            //
            List<Ville> listeCommune = villeRepository.findAll();
            //
            List<Types> listeTypes = typesRepository.findAll();
            model.addAttribute("listeTypes",listeTypes);

            if(choix == 0) {
                // traiter :
                List<Journal> listeJournal = journalRepository.findAllByIdusrAndDatesBetween(utilisateur, dateDBT, dateFIN);
                //
                Utilisateur user = utilisateurRepository.findByIdusr(utilisateur);
                //

                model.addAttribute("histoJournal", histoUtilisateur);
                model.addAttribute("listeJournal", listeJournal);
                model.addAttribute("donnees", 0);
                model.addAttribute("user", user.getNom());
                model.addAttribute("listeCommune",listeCommune);

                model.addAttribute("debut", debut);
                model.addAttribute("fin", fin);
            }
            else if(choix==1){
                // Tous les utilisateurs :
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                List<Object[]> listeJournal = em.createQuery(
                        "select a.nom,b.dates,b.heure,b.action " +
                                "from utilisateur a inner join journal b " +
                                "on a.idusr = b.idusr " +
                                "where b.dates between '"+
                                ClassFonction.retourDate(debut)+"' and '"+
                                ClassFonction.retourDate(fin)+"'").getResultList();

                model.addAttribute("listeJournal", listeJournal);
                model.addAttribute("donneesutilisateurs", 0);

                model.addAttribute("histoJournal", histoUtilisateur);
                model.addAttribute("debut", debut);
                model.addAttribute("fin", fin);
                model.addAttribute("listeCommune",listeCommune);

                em.getTransaction().commit();
                em.close();
            }
            else if(choix==2){
                // Supports créés par l'utilisateur sur une période :
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                List<Object[]> histocreationUtilisateur = em.createQuery(
                        "select a.nom,b.libelle as support, b.datecreation,b.heurecreation," +
                                "b.emplacement,f.libelle as typ," +
                                "c.libelle as secteur,d.libelle as quartier,e.libelle as commune " +
                                "from utilisateur a inner join panneau b on " +
                                "(a.idusr=b.idusr and (b.datecreation between '"+ClassFonction.retourDate(debut)+
                                "' and '"+ClassFonction.retourDate(fin)+"')) " +
                                "inner join secteur c on b.idsec=c.idsec inner join " +
                                "quartier d on c.idquar=d.idqua inner join ville e " +
                                "on d.idvill=e.idvil inner join " +
                                "types f on b.types = f.idtyp where a.idusr="+utilisateur).getResultList();
                em.getTransaction().commit();
                em.close();

                //
                model.addAttribute("histocreationUtilisateur", histocreationUtilisateur);
                model.addAttribute("histoJournal", histoUtilisateur);
                model.addAttribute("debut", debut);
                model.addAttribute("fin", fin);
                model.addAttribute("listeCommune",listeCommune);
            }
            else if(choix==3){
                // Supports créés par l'utilisateur sur une période :
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                List<Object[]> histocreationUtilisateur = em.createQuery(
                        "select a.nom,b.libelle as support, b.datecreation,b.heurecreation," +
                                "b.emplacement,f.libelle as typ," +
                                "c.libelle as secteur,d.libelle as quartier,e.libelle as commune " +
                                "from utilisateur a inner join panneau b on " +
                                "(a.idusr=b.idusr and (b.datecreation between '"+ClassFonction.retourDate(debut)+
                                "' and '"+ClassFonction.retourDate(fin)+"')) " +
                                "inner join secteur c on b.idsec=c.idsec inner join " +
                                "quartier d on c.idquar=d.idqua inner join ville e " +
                                "on d.idvill=e.idvil inner join " +
                                "types f on b.types = f.idtyp").getResultList();
                em.getTransaction().commit();
                em.close();

                //
                model.addAttribute("histocreationUtilisateur", histocreationUtilisateur);
                model.addAttribute("histoJournal", histoUtilisateur);
                model.addAttribute("debut", debut);
                model.addAttribute("fin", fin);
                model.addAttribute("listeCommune",listeCommune);
            }
            else if(choix==4){
                model.addAttribute("histoJournal", histoUtilisateur);
                model.addAttribute("listeCommune",listeCommune);
                model.addAttribute("debut", debut);
                model.addAttribute("fin", fin);
            }
        }
        else if(generer==0){
            // Générer :
            if(choix!=5)
                return "redirect:/genexcel/"+
                        utilisateur+"/"+ClassFonction.retourDate(debut)+"/"+
                        ClassFonction.retourDate(fin)+
                        "/"+choix+"/"+commune+"/"+typesupport;
            else
                return "redirect:/genexcel/"+
                        utilisateur+"/aaa/aaa/"+choix+"/"+commune+"/"+typesupport;
        }
        return "journal";
    }


    // gENERATE excel :
    @GetMapping("/genexcel/{utilisateur}/{debut}/{fin}/{choix}/{commune}/{typesupport}")
    public HttpEntity<byte[]> genereExcel(@PathVariable("utilisateur") int utilisateur,
                                          @PathVariable("debut") String debut,@PathVariable("fin") String fin,
                                          @PathVariable("choix") int choix,@PathVariable("commune") int commune
            ,@PathVariable("typesupport") int typesupport
    ) throws Exception{

        if(choix == 0) {

            Date dateDBT = new SimpleDateFormat("yyyy-MM-dd").
                    parse(debut);
            Date dateFIN = new SimpleDateFormat("yyyy-MM-dd").
                    parse(fin);

            // traiter :
            List<Journal> listeJournal = journalRepository.findAllByIdusrAndDatesBetween(utilisateur, dateDBT, dateFIN);
            //
            Utilisateur user = utilisateurRepository.findByIdusr(utilisateur);
            //

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(user.getIdentifiant());
            Row header = sheet.createRow(0);
            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Dates");
            headerCell = header.createCell(1);
            headerCell.setCellValue("Heure");
            headerCell = header.createCell(2);
            headerCell.setCellValue("Actions");

            // Add data ;
            if(listeJournal.size() > 0) {
                int i = 1;
                Row row;
                for (Journal jl : listeJournal) {
                    row = sheet.createRow(i);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(jl.getDates().toString().replaceAll(" 00:00:00.0",""));
                    cell = row.createCell(1);
                    cell.setCellValue(jl.getHeure());
                    cell = row.createCell(2);
                    cell.setCellValue(jl.getAction());
                    i++;
                }

                ByteArrayOutputStream archivo = new ByteArrayOutputStream();
                workbook.write(archivo);

                if(null!=workbook && null!=archivo) {
                    workbook.close();
                    archivo.close();
                }

                byte[] documentContent = archivo.toByteArray();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportpersonnel.xlsx");
                headers.setContentLength(documentContent.length);
                return new ResponseEntity<byte[]>(documentContent, headers, HttpStatus.OK);
            }
        }
        else if(choix==1){

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List<Object[]> listeJournal = em.createQuery(
                    "select a.nom,b.dates,b.heure,b.action " +
                            "from utilisateur a inner join journal b " +
                            "on a.idusr = b.idusr " +
                            "where b.dates between '"+debut+"' and '"+fin+"'").getResultList();
            em.getTransaction().commit();
            em.close();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("actionutilisateurs");
            Row header = sheet.createRow(0);
            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Utilisateur");
            headerCell = header.createCell(1);
            headerCell.setCellValue("Dates");
            headerCell = header.createCell(2);
            headerCell.setCellValue("Heure");
            headerCell = header.createCell(3);
            headerCell.setCellValue("Actions");

            // Add data ;
            if(listeJournal.size() > 0) {
                int i = 1;
                Row row;
                for (Object[] objet : listeJournal) {
                    row = sheet.createRow(i);
                    Cell cell = row.createCell(0);
                    cell.setCellValue((String)objet[0]);
                    cell = row.createCell(1);
                    cell.setCellValue( String.valueOf(objet[1]).replaceAll(" 00:00:00.0",""));
                    cell = row.createCell(2);
                    cell.setCellValue(String.valueOf(objet[2]));
                    cell = row.createCell(3);
                    cell.setCellValue(String.valueOf(objet[3]));
                    i++;
                }

                ByteArrayOutputStream archivo = new ByteArrayOutputStream();
                workbook.write(archivo);

                if(null!=workbook && null!=archivo) {
                    workbook.close();
                    archivo.close();
                }

                byte[] documentContent = archivo.toByteArray();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportutilisateur.xlsx");
                headers.setContentLength(documentContent.length);
                return new ResponseEntity<byte[]>(documentContent, headers, HttpStatus.OK);
            }
        }
        else if(choix==2){
            // Supports créés par l'utilisateur sur une période :
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List<Object[]> histocreationUtilisateur = em.createQuery(
                    "select a.nom,b.libelle as support, b.datecreation,b.heurecreation," +
                            "b.emplacement,f.libelle as typ," +
                            "c.libelle as secteur,d.libelle as quartier,e.libelle as commune " +
                            "from utilisateur a inner join panneau b on " +
                            "(a.idusr=b.idusr and (b.datecreation between '"+debut+
                            "' and '"+fin+"')) " +
                            "inner join secteur c on b.idsec=c.idsec inner join " +
                            "quartier d on c.idquar=d.idqua inner join ville e " +
                            "on d.idvill=e.idvil inner join " +
                            "types f on b.types = f.idtyp where a.idusr="+utilisateur).getResultList();
            em.getTransaction().commit();
            em.close();

            //
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("supportpersonnel");
            Row header = sheet.createRow(0);
            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Utilisateur");
            headerCell = header.createCell(1);
            headerCell.setCellValue("Support");
            headerCell = header.createCell(2);
            headerCell.setCellValue("Date de création");
            headerCell = header.createCell(3);
            headerCell.setCellValue("Heure de création");
            headerCell = header.createCell(4);
            headerCell.setCellValue("Emplacement");
            headerCell = header.createCell(5);
            headerCell.setCellValue("Type");
            headerCell = header.createCell(6);
            headerCell.setCellValue("Secteur");
            headerCell = header.createCell(7);
            headerCell.setCellValue("Quartier");
            headerCell = header.createCell(8);
            headerCell.setCellValue("Commune");

            // Add data ;
            if(histocreationUtilisateur.size() > 0) {
                int i = 1;
                Row row;
                for (Object[] objet : histocreationUtilisateur) {
                    row = sheet.createRow(i);
                    Cell cell = row.createCell(0);
                    cell.setCellValue((String)objet[0]); // nom
                    cell = row.createCell(1);
                    cell.setCellValue((String)objet[1]); // support
                    cell = row.createCell(2);
                    cell.setCellValue( String.valueOf(objet[2]).replaceAll(" 00:00:00.0",""));
                    cell = row.createCell(3);
                    cell.setCellValue(String.valueOf(objet[3])); // heure
                    cell = row.createCell(4);
                    cell.setCellValue(String.valueOf(objet[4])); // Emplacement

                    cell = row.createCell(5);
                    cell.setCellValue(String.valueOf(objet[5])); // type
                    cell = row.createCell(6);
                    cell.setCellValue(String.valueOf(objet[6])); // secteur
                    cell = row.createCell(7);
                    cell.setCellValue(String.valueOf(objet[7])); // quartier
                    cell = row.createCell(8);
                    cell.setCellValue(String.valueOf(objet[8])); // commune

                    i++;
                }

                ByteArrayOutputStream archivo = new ByteArrayOutputStream();
                workbook.write(archivo);

                if(null!=workbook && null!=archivo) {
                    workbook.close();
                    archivo.close();
                }

                byte[] documentContent = archivo.toByteArray();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportutilisateur.xlsx");
                headers.setContentLength(documentContent.length);
                return new ResponseEntity<byte[]>(documentContent, headers, HttpStatus.OK);
            }
        }
        else if(choix==3){
            // Supports créés par l'utilisateur sur une période :
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List<Object[]> histocreationUtilisateur = em.createQuery(
                    "select a.nom,b.libelle as support, b.datecreation,b.heurecreation," +
                            "b.emplacement,f.libelle as typ," +
                            "c.libelle as secteur,d.libelle as quartier,e.libelle as commune " +
                            "from utilisateur a inner join panneau b on " +
                            "(a.idusr=b.idusr and (b.datecreation between '"+debut+
                            "' and '"+fin+"')) " +
                            "inner join secteur c on b.idsec=c.idsec inner join " +
                            "quartier d on c.idquar=d.idqua inner join ville e " +
                            "on d.idvill=e.idvil inner join " +
                            "types f on b.types = f.idtyp").getResultList();
            em.getTransaction().commit();
            em.close();

            //
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("supportpersonnel");
            Row header = sheet.createRow(0);
            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Utilisateur");
            headerCell = header.createCell(1);
            headerCell.setCellValue("Support");
            headerCell = header.createCell(2);
            headerCell.setCellValue("Date de création");
            headerCell = header.createCell(3);
            headerCell.setCellValue("Heure de création");
            headerCell = header.createCell(4);
            headerCell.setCellValue("Emplacement");
            headerCell = header.createCell(5);
            headerCell.setCellValue("Type");
            headerCell = header.createCell(6);
            headerCell.setCellValue("Secteur");
            headerCell = header.createCell(7);
            headerCell.setCellValue("Quartier");
            headerCell = header.createCell(8);
            headerCell.setCellValue("Commune");

            // Add data ;
            if(histocreationUtilisateur.size() > 0) {
                int i = 1;
                Row row;
                for (Object[] objet : histocreationUtilisateur) {
                    row = sheet.createRow(i);
                    Cell cell = row.createCell(0);
                    cell.setCellValue((String)objet[0]); // nom
                    cell = row.createCell(1);
                    cell.setCellValue((String)objet[1]); // support
                    cell = row.createCell(2);
                    cell.setCellValue( String.valueOf(objet[2]).replaceAll(" 00:00:00.0",""));
                    cell = row.createCell(3);
                    cell.setCellValue(String.valueOf(objet[3])); // heure
                    cell = row.createCell(4);
                    cell.setCellValue(String.valueOf(objet[4])); // Emplacement

                    cell = row.createCell(5);
                    cell.setCellValue(String.valueOf(objet[5])); // type
                    cell = row.createCell(6);
                    cell.setCellValue(String.valueOf(objet[6])); // secteur
                    cell = row.createCell(7);
                    cell.setCellValue(String.valueOf(objet[7])); // quartier
                    cell = row.createCell(8);
                    cell.setCellValue(String.valueOf(objet[8])); // commune

                    i++;
                }

                ByteArrayOutputStream archivo = new ByteArrayOutputStream();
                workbook.write(archivo);

                if(null!=workbook && null!=archivo) {
                    workbook.close();
                    archivo.close();
                }

                byte[] documentContent = archivo.toByteArray();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportutilisateur.xlsx");
                headers.setContentLength(documentContent.length);
                return new ResponseEntity<byte[]>(documentContent, headers, HttpStatus.OK);
            }
        }
        else if(choix==4){
            // Test :
            // Get the list of PANNEAU :
            EntityManager emr = emf.createEntityManager();
            emr.getTransaction().begin();

            // Filtre sur type :
            String mFiltre = (typesupport>0) ? (" and b.types = "+typesupport) : "";
            List<Object[]> histocreationCreees = emr.createQuery(
                    "select a.nom,e.libelle,d.libelle,c.libelle," +
                            "b.datecreation,b.heurecreation,b.image,b.libelle,f.libelle " +
                            "from utilisateur a inner join panneau b on " +
                            "(a.idusr=b.idusr and (b.datecreation between '"+debut+
                            "' and '"+fin+"')) " +
                            "inner join secteur c on b.idsec=c.idsec inner join " +
                            "quartier d on c.idquar=d.idqua inner join ville e " +
                            "on d.idvill=e.idvil inner join " +
                            "types f on b.types = f.idtyp where e.idvil="+commune+mFiltre
            ).getResultList();

            //
            List<TestImage> lTI = new ArrayList<TestImage>();
            TestImage ti = new TestImage();

            ti.setAgent("");
            ti.setCommune("");
            ti.setDates("");
            ti.setHeure("");
            ti.setIllustration("");
            ti.setQuartier("");
            ti.setSecteur("");
            ti.setPanneau("");
            ti.setTypes("");
            lTI.add(ti);

            for(Object[] obj : histocreationCreees) {
                ti = new TestImage();

                ti.setAgent(String.valueOf(obj[0]));
                ti.setCommune(String.valueOf(obj[1]));
                ti.setDates(String.valueOf(obj[4]).replaceAll(" 00:00:00.0",""));
                ti.setHeure(String.valueOf(obj[5]));
                ti.setIllustration(String.valueOf(obj[6]));
                ti.setQuartier(String.valueOf(obj[2]));
                ti.setSecteur(String.valueOf(obj[3]));
                ti.setPanneau(String.valueOf(obj[7]));
                ti.setTypes(String.valueOf(obj[8]));
                lTI.add(ti);

            }

            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(lTI);
            InputStream inputStream =
                    this.getClass().getResourceAsStream("/reports/rapporttest.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // set the parameters :
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("itemdatasource", dataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    parameters, dataSource);

            JRXlsxExporter xlsxExporter = new JRXlsxExporter();
            final byte[] rawBytes;

            try(ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){

                // Set CONFIGURATION :
                SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                configuration.setOnePagePerSheet(false);
                configuration.setRemoveEmptySpaceBetweenRows(true);

                xlsxExporter.setConfiguration(configuration);
                xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
                xlsxExporter.exportReport();

                rawBytes = xlsReport.toByteArray();
            }
            HttpHeaders header = new HttpHeaders();
            final byte[] data;

            data= rawBytes;
            header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapporttest.xlsx");
            header.setContentLength(data.length);
            return new HttpEntity<byte[]>(data, header);

        }
        else if(choix==5){
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            List<Object[]> listeDonnees = em.createQuery(
                    "select d.libelle as commune,e.libelle as types,g.libelle as taill,count(f.idpan) as  " +
                            "total, sum(f.cout) as taxetsp,sum(f.coutodp) as taxeODP from  " +
                            "panneau a inner join secteur b on a.idsec=b.idsec inner join quartier  " +
                            "c on b.idquar=c.idqua inner join ville d on c.idvill=d.idvil inner join  " +
                            "types e on a.types=e.idtyp inner join commande f on a.idpan=f.idpan                  " +
                            "inner join taille g on e.idtail=g.idtai  " +
                            "group by d.libelle,e.libelle,g.libelle").getResultList();

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("coutparville");
            Row header = sheet.createRow(0);
            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Commune");
            headerCell = header.createCell(1);
            headerCell.setCellValue("Support");
            headerCell = header.createCell(2);
            headerCell.setCellValue("Dimension");
            headerCell = header.createCell(3);
            headerCell.setCellValue("Total");
            headerCell = header.createCell(4);
            headerCell.setCellValue("Montant TSP");
            headerCell = header.createCell(5);
            headerCell.setCellValue("Montant ODP");

            // Add data ;
            if(listeDonnees.size() > 0) {
                int i = 1;
                Row row;
                for (Object[] objet : listeDonnees) {
                    row = sheet.createRow(i);
                    Cell cell = row.createCell(0);
                    //System.out.println("objet[0] ---------- : "+objet[0]);
                    cell.setCellValue((String)objet[0]);   // Commune
                    cell = row.createCell(1);
                    cell.setCellValue((String)objet[1]);   // Support
                    cell = row.createCell(2);
                    cell.setCellValue((String)objet[2]);     // Dimension
                    cell = row.createCell(3);
                    cell.setCellValue((Long)objet[3]);     // Total
                    cell = row.createCell(4);
                    cell.setCellValue((Long)objet[4]);     // TSP
                    cell = row.createCell(5);
                    cell.setCellValue((Long)objet[5]);     // ODP
                    i++;
                }
            }


            // Try to add a second sheet :
            List<Object[]> listVillePrix = em.createQuery(
                "select  d.libelle,sum(e.cout) as coutTotal,sum(e.coutodp) as coutTotalOdp from " +
                "commande e inner join panneau a on e.idpan=a.idpan inner join secteur b on a.idsec=b.idsec " +
                "inner join quartier c on b.idquar=c.idqua inner join ville d on c.idvill=d.idvil " +
                "group by d.libelle").getResultList();

            //workbook = new XSSFWorkbook();
            Sheet sheet2 = workbook.createSheet("communeTSPODP");
            Row header2 = sheet2.createRow(0);
            Cell headerCell2 = header2.createCell(0);
            headerCell2.setCellValue("Commune");
            headerCell2 = header2.createCell(1);
            headerCell2.setCellValue("Coût TSP total");
            headerCell2 = header2.createCell(2);
            headerCell2.setCellValue("Coût ODP total");
            headerCell2 = header2.createCell(3);
            headerCell2.setCellValue("Coût total");

            //
            if(listVillePrix.size() > 0) {
                int j = 1;
                Row row;
                for (Object[] objet : listVillePrix) {
                    row = sheet2.createRow(j);
                    Cell cell = row.createCell(0);
                    cell.setCellValue((String)objet[0]);   // Commune
                    cell = row.createCell(1);
                    cell.setCellValue((Long)objet[1]);   // cout total TSP
                    cell = row.createCell(2);
                    cell.setCellValue((Long)objet[2]);     // cout total ODP
                    cell = row.createCell(3);
                    cell.setCellValue( ((Long)objet[1] + (Long)objet[2])  );
                    // Cout total
                    j++;
                }
            }


            //   Aficher le TYPE, le NOMBRE et le MONTANT total
            List<Object[]> listTypeNombre =
                em.createQuery(
                    "select b.libelle,count(a.idpan),sum(c.cout) as ctsp," +
                    "sum(c.coutodp) as codp from " +
                    "panneau a inner join types b on a.types=b.idtyp" +
                    " inner join commande c on a.idpan=c.idpan "+
                    " group by b.libelle").getResultList();
            em.getTransaction().commit();
            em.close();

            //
            Sheet sheet3 = workbook.createSheet("coutParType");
            Row header3 = sheet3.createRow(0);
            Cell headerCell3 = header3.createCell(0);
            headerCell3.setCellValue("Type");
            headerCell3 = header3.createCell(1);
            headerCell3.setCellValue("Nombre de supports");
            headerCell3 = header3.createCell(2);
            headerCell3.setCellValue("Coût TSP total");
            headerCell3 = header3.createCell(3);
            headerCell3.setCellValue("Coût ODP Total");
            headerCell3 = header3.createCell(4);
            headerCell3.setCellValue("Coût Total");

            //
            if(listTypeNombre.size() > 0) {
                int j = 1;
                Row row;
                for (Object[] objet : listTypeNombre) {
                    row = sheet3.createRow(j);
                    Cell cell = row.createCell(0);
                    cell.setCellValue((String)objet[0]);   // Libellé TYPE
                    cell = row.createCell(1);
                    cell.setCellValue((Long)objet[1]);     // Nombre total
                    cell = row.createCell(2);
                    cell.setCellValue((Long)objet[2]);     // cout total TSP
                    cell = row.createCell(3);
                    cell.setCellValue( (Long)objet[3]);    // cout total ODP
                    cell = row.createCell(4);
                    cell.setCellValue( ((Long)objet[2] +
                            (Long)objet[3]));              // cout total
                    // Cout total
                    j++;
                }
            }



            // Get all Villes
            List<Ville> listeTouteVille = villeRepository.findAll();
            Sheet sheet4 = workbook.createSheet("tauxparcommune");
            Row header4 = sheet4.createRow(0);
            Cell headerCell4 = header4.createCell(0);
            headerCell4.setCellValue("Commune");
            headerCell4 = header4.createCell(1);
            headerCell4.setCellValue("Population");
            headerCell4 = header4.createCell(2);
            headerCell4.setCellValue("Taux ODP");
            headerCell4 = header4.createCell(3);
            headerCell4.setCellValue("Taux TSP Affiches Lumineuses");
            headerCell4 = header4.createCell(4);
            headerCell4.setCellValue("Taux TSP Affiches peintes");
            headerCell4 = header4.createCell(5);
            headerCell4.setCellValue("Banderoles");
            if(listeTouteVille != null) {
                int j = 1;
                Row row;
                for (Ville ville : listeTouteVille) {
                    row = sheet4.createRow(j);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(ville.getLibelle());   // Libellé COMMUNE
                    cell = row.createCell(1);
                    cell.setCellValue(ville.getPopulation());     // Population
                    cell = row.createCell(2);
                    cell.setCellValue(ville.getTaux());     // taxe ODP
                    cell = row.createCell(3);
                    cell.setCellValue(
                        getTaxes(ville.getPopulation(),
                                1));     // TSP Affiches Lumineuses
                    cell = row.createCell(4);
                    cell.setCellValue(
                            getTaxes(ville.getPopulation(),
                                    2));     // TSP Affiches peintes
                    cell = row.createCell(5);
                    cell.setCellValue(
                            getTaxes(ville.getPopulation(),
                                    3));     // Banderoles
                    //
                    j++;
                }

                //
                ByteArrayOutputStream archivo = new ByteArrayOutputStream();
                workbook.write(archivo);

                if(null!=workbook && null!=archivo) {
                    workbook.close();
                    archivo.close();
                }

                byte[] documentContent = archivo.toByteArray();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
                headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportcout.xlsx");
                headers.setContentLength(documentContent.length);
                return new ResponseEntity<byte[]>(documentContent, headers, HttpStatus.OK);

            }
        }

        return null;
    }


    // Get Rapport final
    @GetMapping(value="/getrapportfinal")
    public ResponseEntity<byte[]> getrapportfinal(){
        //
        try {
            InputStream inputStream =
                    this.getClass().getResourceAsStream("/templates/rapportfin.xlsx");
            byte[] documentContent = IOUtils.toByteArray(inputStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=rapportfin.xlsx");
            headers.setContentLength(documentContent.length);
            return new ResponseEntity<byte[]>(documentContent, headers, HttpStatus.OK);
        }
        catch (Exception exc){
            System.out.println("Exception : "+exc.toString());
        }

        return null;
    }




    private int getTaxes(int population, int typesupport){

        int retour = 0;
        switch (typesupport){
            case 1:
                // Affiches Lumineuses
                if (population < 50000) retour = 1000;
                else if (population <= 200000) retour = 2000;
                else if (population > 200000) retour = 3000;
                break;

            case 2:
                // Affiches peintes
                if (population < 50000) retour = 250;
                else if (population <= 200000) retour = 500;
                else if (population > 200000) retour = 1000;
                break;

            case 3:
                // Banderoles
                if (population < 50000) retour = 0;
                else if (population <= 200000) retour = 2500;
                else if (population > 200000) retour = 5000;
                break;
        }

        return retour;
    }


    @GetMapping(value="/histopubli")
    public String interfacehistopubli(){
        //
        return "historiquepublication";
    }

    //
    @PostMapping(value="/soumhistpubli")
    public String soumhistpubli(
            @RequestParam("debut") String debut,
            @RequestParam("fin") String fin,
            Model model) throws Exception {
        // Get the dates :
        Date dateDBT = new SimpleDateFormat("yyyy-MM-dd").
                parse(ClassFonction.retourDate(debut));
        String dateDT = new SimpleDateFormat("yyyy-MM-dd").format(dateDBT);
        Date dateFIN = new SimpleDateFormat("yyyy-MM-dd").
                parse(ClassFonction.retourDate(fin));
        String dateFN = new SimpleDateFormat("yyyy-MM-dd").format(dateFIN);

        // traiter :
        EntityManager emr = emf.createEntityManager();
        emr.getTransaction().begin();
        List <Object[]> histoCommande = emr.createQuery(
                "select c.libelle,b.pdtpublier,b.cout,b.datedebut,b.datefin,b.idcom " +
                        "from client a inner join commande b on a.idcli=b.idcli inner join " +
                        "panneau c on b.idpan=c.idpan where b.datefin between '"+dateDT+"' and '"+dateFN+"'").getResultList();
        emr.getTransaction().commit();
        model.addAttribute("histoCommande",histoCommande);
        model.addAttribute("datedebut",debut);
        model.addAttribute("datefin",fin);
        emr.close();
        return "historiquepublication";
    }

    public void saveJournal(int user,String action){
        Journal jrl = new Journal();
        jrl.setIdusr(user);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String heure = new SimpleDateFormat("HH:mm:ss").format(new Date());
        try {
            jrl.setDates(new SimpleDateFormat("yyyy-MM-dd").
                    parse(date));
            jrl.setHeure(heure);
        }
        catch (Exception exc){
        }
        jrl.setAction(action);
        journalRepository.save(jrl);
    }


    // Liste des utilisateurs
    @GetMapping(value="/parametres")
    public String getparametres(Model model){
        Parametres prm = parametresRepository.findByIdpar(1);
        model.addAttribute("prm", prm);
        return "parametres";
    }

    @GetMapping(value="/modifprms")
    public String getmodifprms(Model model){
        Parametres prm = parametresRepository.findByIdpar(1);
        model.addAttribute("prm", prm);
        model.addAttribute("modifparam", "modifparam");
        return "parametres";
    }


    @PostMapping(value="/modifierparametres")
    public String setmodifierparametres(Model model,
                                        @RequestParam("enregsupport") int enregsupport){
        Parametres prm = parametresRepository.findByIdpar(1);
        // Make the update :
        prm.setEnregsupport(enregsupport);
        parametresRepository.save(prm);
        model.addAttribute("prm", prm);
        return "parametres";
    }



    @PostMapping(value="/pposition")
    @ResponseBody
    public String pposition(@RequestParam("user") String user,
                                       @RequestParam("latitude") Double latitude,
                                       @RequestParam("longitude") Double longitude){
        // check if this data is in the LIST :
        UserPosition up = new UserPosition(user,latitude, longitude,0);
        if(!listeUserPosition.contains(up)){
            // Does not exist :
            listeUserPosition.add(up);
        }
        else{
            listeUserPosition.remove(up);
            listeUserPosition.add(up);
        }
        return "ok";
    }









}
