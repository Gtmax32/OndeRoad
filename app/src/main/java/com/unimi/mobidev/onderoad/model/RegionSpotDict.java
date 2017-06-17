package com.unimi.mobidev.onderoad.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Giuseppe Fabio Trentadue on 01/10/2016.
 */

public class RegionSpotDict {

    private static final TreeMap<String, List<SpotInfo>> DICT;

    static {
        DICT = new TreeMap<>();
        DICT.put("Toscana", Arrays.asList(new SpotInfo("Toscana", "Ms", "Marina di carrara", "Idrovora", 44.0359679, 10.043825599999991, 0, "LATO NORD DEL PORTO DI MARINA DI CARRARA. ATTENZIONE DIVIETO DI BALNEAZIONE E ATTIVITA' NAUTICO SPORTIVE", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO", "SABBIA")),
                new SpotInfo("Toscana", "Ms", "Cinquale", "Il trabucco", 43.9828071, 10.153797199999985, 0, "AFFIANCO AL RISTORANTE TRABUCCO. LO SPOT PRINCIPALE E' ACCANTO AL MOLETTO DI MASSI", new SpotInfoTable("DX", "S-O (MODERATO)", "SO", "SABBIA")),
                new SpotInfo("Toscana", "Lu", "Lido di camaiore", "Lido di camaiore", 43.9050586, 10.229172500000004, 0, "LUNGO LA SPIAGGIA VARI PICCHI SU SECCHE VARIABILI A DESTRA E SINISTRA DEL MOLO", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO", "SABBIA")),
                new SpotInfo("Toscana", "Lu", "Viareggio", "Piazzale mazzini", 43.8657267, 10.2513103, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Toscana", "Lu", "Viareggio", "Foce del fosso dell'abate", 43.8657267, 10.2513103, 0, "SUL VIALE AL MARE, PICCHI SPARSI NON REGGE GRANDI MISURA", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO", "SABBIA")),
                new SpotInfo("Toscana", "Lu", "Viareggio", "Tito del molo", 43.8657267, 10.2513103, 0, "SUL LATO NORD DEL MOLO DEL PORTO. LAVORA MEGLIO CON VENTO E MARE ATTIVO", new SpotInfoTable("SX", "S-O (ATTIVO)", "SO", "SABBIA")),
                new SpotInfo("Toscana", "Lu", "Viareggio", "Darsena s/o", 43.8657267, 10.2513103, 0, "SULLA LUNGA SPIAGGIA A SUD DI VIAREGGIO VARI PICCHI SU SECCHE VARIABILI", new SpotInfoTable("DX-SX", "- (ASSENTE)", "SO", "SABBIA")),
                new SpotInfo("Toscana", "Pi", "Tirrenia", "Tirrenia", 43.6294361, 10.29086319999999, 0, "VARI PICCHI SU SABBIA REGGE POCO CON MARE ATTIVO", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO", "SABBIA")),
                new SpotInfo("Toscana", "Li", "Livorno", "Bagni fiume", 43.548473, 10.310567399999968, 0, "A SUD DEL PORTO, ACCANTO ALL'ACCADEMIA NAVALE E DIFRONTE ALL'IPODROMO", new SpotInfoTable("DX", "S-O (MODERATO)", "SO-O", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Livorno", "Biscottino", 43.548473, 10.310567399999968, 0, "A SUD DEI BAGNI FIUME, DAVANTI AI TRE PONTI VICINO ALLA SCUOLA DI WIDSURF, ADATTO A LONGBOARD", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO-O", "SABBIA")),
                new SpotInfo("Toscana", "Li", "Livorno", "Maroccone", 43.548473, 10.310567399999968, 0, "A SUD DI LIVORNO IN UNO SPIAZZO CON LE INDICAZIONI PER MARINA DEL BOCCALE A 200MT DAL CAMPING MIRAMARE", new SpotInfoTable("SX", "S-O (MODERATO)", "SO-O", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Livorno", "Il sale", 43.548473, 10.310567399999968, 0, "SPIAGGIA DEL SALE A DESTRA DEI BAGNI ROMA ONDA CORTA MA POTENTE TACK OFF DIFFICILE", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO-O", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Quercianella", "Il golfetto", 43.4592601, 10.36780120000003, 0, "A SINISTRA NEL GOLFETTO TRA IL SALE E I BAGNI ROMA", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO-O", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Quercianella", "Bagni roma", 43.4592601, 10.36780120000003, 0, "DAVANTI AI BAGNI ROMA NON REGGE MARE ATTIVO", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO-O", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Quercianella", "Lido del rogiolo", 43.4592601, 10.36780120000003, 0, "NELL'INSENATURA A SINISTRA DEI BAGNI LIDO DEL ROGIOLO", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO-O", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Quercianella", "Bagni paolieri", 43.4592601, 10.36780120000003, 0, "OTTIMA SINISTRA CHE ENTRA NELLA CALETTA DEI BAGNI ", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "NO-O", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Quercianella", "Porto", 43.4592601, 10.36780120000003, 0, "AFFIANCO AL PORTICCIOLO DI QUERCIANELLA TACK OFF VELOCE", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "NO-O", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Quercianella", "Chioma", 43.4592601, 10.36780120000003, 0, "AL PORTICCIOLO DI CHIOMA A SUD DEL PAESE RICHIEDE GRANDI MAREGGIATE", new SpotInfoTable("DX", "S-O (ATTIVO)", "NO-O", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Castiglioncello", "Bagni italia", 43.4047359, 10.418585500000063, 0, "LATO NORD DELLA BAIA DEL BAGNO ITALIA RIPARATO DAL VENTO", new SpotInfoTable("DX-SX", "S-O (ATTIVO)", "SO-O", "MISTO")),
                new SpotInfo("Toscana", "Li", "Rosignano", "Garagolo", 43.387968, 10.438226600000007, 0, "LATO NORD DEL PORTO DI ROSIGNANO IN CORRISPONDENZA DI UNA NAVE INCAGLIATA", new SpotInfoTable("DX", "S-O (MODERATO)", "SO-O", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Rosignano", "Scoglietto, trieste", 43.387968, 10.438226600000007, 0, "LUNGO IL LITORALE DI ROSIGNANO SOLVAY SEQUENZA DI REEF IDEALE IN SCADUTA DI LIBECCIO", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO-O-NO", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Rosignano", "Lillatro", 43.387968, 10.438226600000007, 0, "TRE PICCHI SITUATI NEL GOLFO DI FRONTE ALLO STABILIMENTO DELLA SOLVAY. CON VENTO ATTIVO LAVORA SOLO IL PICCO Più A SINISTRA", new SpotInfoTable("DX-SX", "S-O (ATTIVO)", "SO-O-NO", "ROCCIA")),
                new SpotInfo("Toscana", "Li", "Piombino", "Perelli", 42.9256335, 10.525889099999972, 0, "LUNGO LA SPIAGGIA LAVORA CON MAREGGIATE DI S,S/E O MARE ENORME DA O. NON BUONO CON MARE ATTIVO DA S/E, S/O", new SpotInfoTable("DX-SX", "S-O (MODERATO)", "SO-O SE", "SABBIA")),
                new SpotInfo("Toscana", "Li", "Ansedonia", "Ansedonia", 42.40812469999999, 11.28329629999996, 0, "SPIAGGIA A NORD DEL CENTRO ABITATO PICCHI SPARSI ANCHE NELLA SPIAGGIA A SUD DEL MONTE", new SpotInfoTable("SX", "S-O (MODERATO)", "SE-S", "ROCCIA"))));

        DICT.put("Liguria", Arrays.asList(new SpotInfo("Liguria", "Im", "Ventimiglia", "Le calandre", 43.7912366, 7.607586399999946, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Im", "Sanremo", "Tre ponti", 43.81596709999999, 7.7760567000000265, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Im", "Sanremo", "Bistrot", 43.81596709999999, 7.7760567000000265, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Im", "Diano", "Diano", 43.9088641, 8.082802399999991, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Sv", "Andora", "Molo", 43.9848226, 8.130599200000006, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Sv", "Alassio", "Alassio", 44.014336, 8.181174100000021, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Sv", "Savona", "Il garden", 44.2975603, 8.464500000000044, 0, "RISTORANTE", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Sv", "Varazze", "Pool", 44.3594336, 8.577312600000027, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Sv", "Varazze", "Varazze reef", 44.3594336, 8.577312600000027, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Sv", "Varazze", "Backdoor", 44.3594336, 8.577312600000027, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Sv", "Arenzano", "Marina grande", 44.4058612, 8.686016699999982, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Ge", "Genova", "Capo marina", 44.4056499, 8.946255999999948, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Ge", "Recco", "Recco", 44.3614219, 9.143744500000025, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Ge", "Bogliasco", "Bogliasco", 44.3792927, 9.064765299999976, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Ge", "Chiavari", "Entella", 44.3168416, 9.319982200000027, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Ge", "Moneglia", "La secca", 44.239147, 9.488617699999963, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Sp", "Levanto", "Levanto", 44.1686141, 9.610654000000068, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Liguria", "Sp", "Lerici", "Il castello - la venere", 44.0756332, 9.916933699999959, 0, "", new SpotInfoTable("", "", "", ""))));

        DICT.put("Campania", Arrays.asList(new SpotInfo("Campania", "Na", "Procida", "Il lido", 40.7622633, 14.030954500000007, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Campania", "Sa", "Salerno", "Lungomare ovest", 40.68244079999999, 14.76809609999998, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Campania", "Sa", "Salerno", "Irno", 40.68244079999999, 14.76809609999998, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Campania", "Sa", "Salerno", "Yasmina beach", 40.68244079999999, 14.76809609999998, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Campania", "Sa", "Salerno", "Lido aurora", 40.68244079999999, 14.76809609999998, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Campania", "Sa", "Salerno", "Lido lago", 40.68244079999999, 14.76809609999998, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Campania", "Sa", "Salerno", "Lido la vela", 40.68244079999999, 14.76809609999998, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Campania", "Sa", "Acciaroli", "Acciaroli", 40.1762064, 15.03262989999996, 0, "", new SpotInfoTable("", "", "", ""))));

        DICT.put("Lazio", Arrays.asList(new SpotInfo("Lazio", "Roma", "Civitavecchia", "L'ideale", 42.0924239, 11.795413199999984, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Roma", "Civitavecchia", "Piccolo paradiso", 42.0924239, 11.795413199999984, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Roma", "Civitavecchia", "Il marangone e 012", 42.0924239, 11.795413199999984, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Roma", "Fregene", "Fregene", 41.8558992, 12.198098999999957, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Roma", "Focene", "Focene", 41.817242, 12.211542099999974, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Roma", "Ostia", "Lido di ostia", 41.7311182, 12.286269400000037, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Roma", "Anzio", "Soldati", 41.4495955, 12.619725300000027, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Roma", "Anzio", "Porto o marinetti", 41.4495955, 12.619725300000027, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Roma", "Nettuno", "Belvedere", 41.4577305, 12.665491400000064, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Roma", "Nettuno", "Chiesa", 41.4577305, 12.665491400000064, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Roma", "Nettuno", "Poligono", 41.4577305, 12.665491400000064, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Latina", "Sabaudia", "Lilanda'", 41.3015823, 13.027575800000022, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Latina", "Sperlonga", "Porto", 41.2636741, 13.427141399999982, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Latina", "Sperlonga", "Mare", 41.2636741, 13.427141399999982, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Latina", "Gaeta", "S. Agostino", 41.2107299, 13.571428500000025, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Latina", "Gaeta", "San vito", 41.2107299, 13.571428500000025, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Latina", "Gaeta", "Scissure", 41.2107299, 13.571428500000025, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Latina", "Gaeta", "Ariana", 41.2107299, 13.571428500000025, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Lazio", "Latina", "Gaeta", "Separo", 41.2107299, 13.571428500000025, 0, "", new SpotInfoTable("", "", "", ""))));

        DICT.put("Sardegna", Arrays.asList(new SpotInfo("Sardegna", "Sassari", "Stintino", "Roachland", 40.9401332, 8.223588899999982, 0, "A 4 KM A OVEST DI STINTINO. ATTENZIONE ALLE CORRENTI", new SpotInfoTable("DX", "MODERATO", "SO-O-NO", "ROCCIA")),
                new SpotInfo("Sardegna", "Sassari", "Alghero", "Porto ferro", 40.5579517, 8.319294899999932, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Sassari", "Alghero", "Palo blanco", 40.5579517, 8.319294899999932, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Sassari", "Alghero", "Le bombarde", 40.5579517, 8.319294899999932, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Sassari", "Alghero", "Maddalenedda", 40.5579517, 8.319294899999932, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Sassari", "Alghero", "Torre dei cani", 40.5579517, 8.319294899999932, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Sassari", "Alghero", "Cannoni", 40.5579517, 8.319294899999932, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Sassari", "Alghero", "El ventral", 40.5579517, 8.319294899999932, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Sassari", "Alghero", "La speranza", 40.5579517, 8.319294899999932, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Sassari", "Alghero", "I piastroni", 40.5579517, 8.319294899999932, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Buggerru", "Portixxedu", 39.399722, 8.402429999999981, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Buggerru", "San nicolo'", 39.399722, 8.402429999999981, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Buggerru", "Il molo", 39.399722, 8.402429999999981, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Chia", "La torre", 38.90813190000001, 8.876944900000012, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Chia", "Flamingo", 38.90813190000001, 8.876944900000012, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Chia", "Cala cipolla", 38.90813190000001, 8.876944900000012, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Chia", "Pipeline", 38.90813190000001, 8.876944900000012, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Chia", "L'isolotto", 38.90813190000001, 8.876944900000012, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Chia", "Il morto", 38.90813190000001, 8.876944900000012, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Chia", "Il pontile", 38.90813190000001, 8.876944900000012, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Cagliari", "Poetto", 39.2136846, 9.171179499999994, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Capitana", "Il fortino", 39.2093022, 9.310803999999962, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sardegna", "Cagliari", "Capitana", "Pocca point", 39.2093022, 9.310803999999962, 0, "", new SpotInfoTable("", "", "", ""))));

        DICT.put("Calabria", Arrays.asList(new SpotInfo("Calabria", "Vibo valentia", "Zambrone", "La spiaggia", 38.7054031, 15.969562200000041, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Calabria", "Reggio calabria", "Siderno", "Renault", 38.2693662, 16.298308499999962, 0, "", new SpotInfoTable("", "", "", ""))));

        DICT.put("Sicilia", Arrays.asList(new SpotInfo("Sicilia", "Catania", "Catania", "La playa", 37.5078772, 15.083030399999984, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sicilia", "Palermo", "Cinisi", "Il reef", 38.155087, 13.108809699999938, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sicilia", "Palermo", "Cinisi", "Magaggiari", 38.155087, 13.108809699999938, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sicilia", "Palermo", "Trappeto", "Ciammarita", 38.068732, 13.042513900000017, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Sicilia", "Agrigento", "Sciacca", "San marco e ciminiera", 37.5085148, 13.08163049999996, 0, "", new SpotInfoTable("", "", "", ""))));

        DICT.put("Puglia", Arrays.asList(new SpotInfo("Puglia", "Bari", "Brindisi", "Capo torre cavallo", 40.6327278, 17.941761600000063, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Bari", "Brindisi", "Vigili del fuoco", 40.6327278, 17.941761600000063, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Bari", "Brindisi", "Lido granchio rosso", 40.6327278, 17.941761600000063, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Bari", "Monopoli", "Capitolo", 40.9525166, 17.298555899999997, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Bari", "Bari", "Tombino", 41.1171432, 16.871871499999997, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Foggia", "Vieste", "La gattarella", 41.8824764, 16.176748900000007, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Foggia", "Vieste", "Pizzomunno", 41.8824764, 16.176748900000007, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Foggia", "Vieste", "Marina piccola", 41.8824764, 16.176748900000007, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Foggia", "Vieste", "Lo sperone", 41.8824764, 16.176748900000007, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Foggia", "Vieste", "La capuzza", 41.8824764, 16.176748900000007, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Foggia", "Vieste", "Punta lunga", 41.8824764, 16.176748900000007, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Puglia", "Foggia", "Vieste", "Baia molinella", 41.8824764, 16.176748900000007, 0, "", new SpotInfoTable("", "", "", ""))));

        DICT.put("Molise", Arrays.asList(new SpotInfo("Molise", "Campobasso", "Campomarino", "Porto turistico", 41.9592663, 15.051268199999981, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Molise", "Campobasso", "Termoli", "Rio vivo", 42.0005331, 14.995283900000004, 0, "", new SpotInfoTable("", "", "", ""))));

        DICT.put("Abruzzo", Arrays.asList(new SpotInfo("Abruzzo", "Chieti", "Vasto", "Porto", 42.0993726, 14.720308499999987, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Abruzzo", "Chieti", "Fossacesia marina", "Chiesetta", 42.0993726, 14.720308499999987, 0, "", new SpotInfoTable("", "", "", "")),
                new SpotInfo("Abruzzo", "Chieti", "Ortona", "Acquabella", 42.3522441, 14.40281129999994, 0, "", new SpotInfoTable("", "", "", ""))));

        DICT.put("Marche", Arrays.asList(new SpotInfo("Marche", "Ascoli piceno", "Grottammare", "Tesino", 42.9853425, 13.8683671, 0, "", new SpotInfoTable("", "", "", "")),
        new SpotInfo("Marche", "Ancona", "Sirolo", "Il sasso", 43.5191009, 13.621703400000001, 0, "", new SpotInfoTable("", "", "", "")),
        new SpotInfo("Marche", "Ancona", "Portonovo", "La nave", 43.5611908, 13.599936999999954, 0, "", new SpotInfoTable("", "", "", "")),
        new SpotInfo("Marche", "Ancona", "Portonovo", "Giacchetti", 43.5611908, 13.599936999999954, 0, "", new SpotInfoTable("", "", "", "")),
        new SpotInfo("Marche", "Ancona", "Portonovo", "La torre", 43.5611908, 13.599936999999954, 0, "", new SpotInfoTable("", "", "", "")),
        new SpotInfo("Marche", "Ancona", "Portonovo", "Il molo", 43.5611908, 13.599936999999954, 0, "", new SpotInfoTable("", "", "", "")),
        new SpotInfo("Marche", "Ancona", "Portonovo", "Trocadero", 43.5611908, 13.599936999999954, 0, "", new SpotInfoTable("", "", "", "")),
        new SpotInfo("Marche", "Ancona", "Senigallia", "Porto", 43.7197926, 13.215222400000016, 0, "", new SpotInfoTable("", "", "", "")),
        new SpotInfo("Marche", "Ancona", "Senigallia", "Piramidi", 43.7197926, 13.215222400000016, 0, "", new SpotInfoTable("", "", "", "")),
        new SpotInfo("Marche", "Pesaro urbino", "Fano", "Sassonia", 43.8398164, 13.019420099999934, 0, "", new SpotInfoTable("", "", "", "")),
        new SpotInfo("Marche", "Pesaro urbino", "Fano", "Lido", 43.8398164, 13.019420099999934, 0, "", new SpotInfoTable("", "", "", ""))));

        DICT.put("Veneto", Arrays.asList(new SpotInfo("Veneto", "Venezia", "Jesolo", "Iesolo", 45.5334198, 12.643849799999998, 0, "", new SpotInfoTable("", "", "", ""))));


    }

    public static ArrayList<String> getKeys() {
        ArrayList<String> keys = new ArrayList<>();

        for (String key : DICT.keySet()) {
            keys.add(key);
        }

        return keys;
    }

    public static ArrayList<SpotInfo> getElemFromKey(String key) {
        if (DICT.containsKey(key)) {

            return new ArrayList<>(DICT.get(key));
        }

        return null;
    }

    public static ArrayList<String> getNameListFromKey(String key) {
        ArrayList<String> nameList = new ArrayList<>();

        if (DICT.containsKey(key)) {
            for (SpotInfo elem : DICT.get(key)) {
                nameList.add(elem.getTitle());
            }

            return nameList;
        }

        return null;
    }

}
