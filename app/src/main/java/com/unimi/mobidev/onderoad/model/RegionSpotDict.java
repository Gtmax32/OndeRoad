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
        DICT.put("Liguria", Arrays.asList(new SpotInfo("Alassio", 44.014336, 8.181174100000021, 0, null), new SpotInfo("Albenga", 44.0494254, 8.215611299999978, 0, null), new SpotInfo("Andora", 43.9848226, 8.130599200000006, 0, null), new SpotInfo("Arenzano", 44.4058612, 8.686016699999982, 0, null), new SpotInfo("Bocca", 44.0536506, 9.976260700000012, 0, null), new SpotInfo("Bogliasco", 44.3792927, 9.064765299999976, 0, null), new SpotInfo("Camogli", 44.3542792, 9.149817799999937, 0, null), new SpotInfo("Celle", 52.6086266, 10.008386400000063, 0, null), new SpotInfo("Chiavari", 44.3168416, 9.319982200000027, 0, null), new SpotInfo("Diano", 43.9088641, 8.082802399999991, 0, null), new SpotInfo("Finale", 44.431648, 8.791339999999991, 0, null), new SpotInfo("Genova", 44.4056499, 8.946255999999948, 0, null), new SpotInfo("Imperia", 43.8896857, 8.039516999999933, 0, null), new SpotInfo("Lerici", 44.0756332, 9.916933699999959, 0, null), new SpotInfo("Levanto", 44.1686141, 9.610654000000068, 0, null), new SpotInfo("Loano", 44.13064079999999, 8.260967100000016, 0, null), new SpotInfo("Moneglia", 44.239147, 9.488617699999963, 0, null), new SpotInfo("Pietra", 44.1502851, 8.283452300000022, 0, null), new SpotInfo("Portofino", 44.3031559, 9.209787900000038, 0, null), new SpotInfo("Portovenere", 44.0541257, 9.836628000000019, 0, null), new SpotInfo("Rapallo", 44.3498211, 9.23388890000001, 0, null), new SpotInfo("Recco", 44.3614219, 9.143744500000025, 0, null), new SpotInfo("Sanremo", 43.81596709999999, 7.7760567000000265, 0, null), new SpotInfo("Sarzana", 44.111424, 9.963121499999943, 0, null), new SpotInfo("Savona", 44.2975603, 8.464500000000044, 0, null), new SpotInfo("Sestri", 44.2763654, 9.400828100000012, 0, null), new SpotInfo("Vado", 44.2691047, 8.439781900000071, 0, null), new SpotInfo("Varazze", 44.3594336, 8.577312600000027, 0, null), new SpotInfo("Ventimiglia", 43.7912366, 7.607586399999946, 0, null), new SpotInfo("Zoagli", 44.3364754, 9.268170999999938, 0, null)));
        DICT.put("Toscana", Arrays.asList(new SpotInfo("Forte", 43.95796170000001, 10.167279199999939, 0, null), new SpotInfo("Livorno", 43.548473, 10.310567399999968, 0, null), new SpotInfo("Marina di Carrara", 44.0359679, 10.043825599999991, 0, null), new SpotInfo("Marina di Massa", 44.0273656, 10.068718200000035, 0, null), new SpotInfo("Marina di Pietrasanta", 43.9306952, 10.206750100000022, 0, null), new SpotInfo("Piombino", 42.9256335, 10.525889099999972, 0, null), new SpotInfo("Ansedonia", 42.40812469999999, 11.28329629999996, 0, null), new SpotInfo("Antignano", 43.498668, 10.326764000000026, 0, null), new SpotInfo("Baratti", 42.9990764, 10.517120400000067, 0, null), new SpotInfo("Capraia", 43.7350466, 11.015325399999938, 0, null), new SpotInfo("Castiglioncello", 43.4047359, 10.418585500000063, 0, null), new SpotInfo("Cinquale", 43.9828071, 10.153797199999985, 0, null), new SpotInfo("Marina di Grosseto", 42.7151275, 10.984806400000025, 0, null), new SpotInfo("Lido Di Camaiore", 43.9050586, 10.229172500000004, 0, null), new SpotInfo("Pratoranieri", 42.9406934, 10.713646599999947, 0, null), new SpotInfo("Quercianella", 43.4592601, 10.36780120000003, 0, null), new SpotInfo("Rosignano", 43.387968, 10.438226600000007, 0, null), new SpotInfo("Tirrenia", 43.6294361, 10.29086319999999, 0, null), new SpotInfo("Viareggio", 43.8657267, 10.2513103, 0, null)));
        DICT.put("Lazio", Arrays.asList(new SpotInfo("Anzio", 41.4495955, 12.619725300000027, 0, null), new SpotInfo("Campascolano", 41.6555633, 12.42802800000004, 0, null), new SpotInfo("Marina di Cerveteri", 41.9803955, 12.05040699999995, 0, null), new SpotInfo("Civitavecchia", 42.0924239, 11.795413199999984, 0, null), new SpotInfo("Fiumicino", 41.7735409, 12.239711700000043, 0, null), new SpotInfo("Focene", 41.817242, 12.211542099999974, 0, null), new SpotInfo("Fregene", 41.8558992, 12.198098999999957, 0, null), new SpotInfo("Gaeta", 41.2107299, 13.571428500000025, 0, null), new SpotInfo("Ladispoli", 41.9498965, 12.076104499999929, 0, null), new SpotInfo("Latina", 41.4675671, 12.903596500000049, 0, null), new SpotInfo("Lavinio", 41.4997397, 12.588638899999978, 0, null), new SpotInfo("Montalto Marina", 42.3220694, 11.586134600000037, 0, null), new SpotInfo("Nettuno", 41.4577305, 12.665491400000064, 0, null), new SpotInfo("Ostia", 41.7311182, 12.286269400000037, 0, null), new SpotInfo("Passoscuro", 41.8986149, 12.165179800000033, 0, null), new SpotInfo("Sabaudia", 41.3015823, 13.027575800000022, 0, null), new SpotInfo("Sperlonga", 41.2636741, 13.427141399999982, 0, null), new SpotInfo("Terracina", 41.2963728, 13.233265699999947, 0, null), new SpotInfo("Torvaianica", 41.6208388, 12.46263870000007, 0, null)));
        DICT.put("Sardegna", Arrays.asList(new SpotInfo("Vignola Mare", 41.1270014, 9.063050999999973, 0, null), new SpotInfo("Alghero", 40.5579517, 8.319294899999932, 0, null), new SpotInfo("Badesi Mare", 40.971573, 8.863142000000039, 0, null), new SpotInfo("Buggerru", 39.399722, 8.402429999999981, 0, null), new SpotInfo("Cagliari", 39.2136846, 9.171179499999994, 0, null), new SpotInfo("Calasetta", 39.10979409999999, 8.369288900000015, 0, null), new SpotInfo("Capitana", 39.2093022, 9.310803999999962, 0, null), new SpotInfo("Museddu", 39.7762992, 9.660178299999984, 0, null), new SpotInfo("Sant'Elmo", 39.2151, 9.565261700000065, 0, null), new SpotInfo("Chia", 38.90813190000001, 8.876944900000012, 0, null), new SpotInfo("Costa Rei", 39.2500991, 9.570129599999973, 0, null), new SpotInfo("Santa Margherita di Pula", 38.930935, 8.925914199999966, 0, null), new SpotInfo("Oristano", 39.8676904, 8.558367599999997, 0, null), new SpotInfo("Stintino", 40.9401332, 8.223588899999982, 0, null), new SpotInfo("Cala Caterina", 39.1089509, 9.508258100000035, 0, null)));
        DICT.put("Campania", Arrays.asList(new SpotInfo("Acciaroli", 40.1762064, 15.03262989999996, 0, null), new SpotInfo("Agropoli", 40.3486827, 14.991219499999943, 0, null), new SpotInfo("Palinuro", 40.03930800000001, 15.290936399999964, 0, null), new SpotInfo("Pozzuoli", 40.8459469, 14.0932861, 0, null), new SpotInfo("Procida", 40.7622633, 14.030954500000007, 0, null), new SpotInfo("Salerno", 40.68244079999999, 14.76809609999998, 0, null)));
        DICT.put("Calabria", Arrays.asList(new SpotInfo("Botricello", 38.9348164, 16.8554977, 0, null), new SpotInfo("Caminia", 38.7450242, 16.556197399999974, 0, null), new SpotInfo("Cittadella", 39.5460297, 15.882038899999998, 0, null), new SpotInfo("Cropani", 38.96781259999999, 16.781420799999978, 0, null), new SpotInfo("Crotone", 39.0807932, 17.12711020000006, 0, null), new SpotInfo("Galati", 37.9318063, 16.06800640000006, 0, null), new SpotInfo("Falerna Marina", 39.0038688, 16.130160199999978, 0, null), new SpotInfo("Nocera Scalo", 39.0156941, 16.118399899999986, 0, null), new SpotInfo("Pellaro", 38.0185547, 15.642718899999977, 0, null), new SpotInfo("Pizzo", 38.7337855, 16.162892100000022, 0, null), new SpotInfo("Punta", 38.7188142, 16.05667010000002, 0, null), new SpotInfo("Siderno", 38.2693662, 16.298308499999962, 0, null), new SpotInfo("Simeri Mare", 38.86560679999999, 16.69766549999997, 0, null), new SpotInfo("Squillace", 38.7787519, 16.569456100000025, 0, null), new SpotInfo("Zambrone", 38.7054031, 15.969562200000041, 0, null)));
        DICT.put("Sicilia", Arrays.asList(new SpotInfo("Agrigento", 37.2871465, 13.557429599999978, 0, null), new SpotInfo("Aspra", 38.104746, 13.498790999999983, 0, null), new SpotInfo("Avola", 36.9095319, 15.13488910000001, 0, null), new SpotInfo("Catania", 37.5078772, 15.083030399999984, 0, null), new SpotInfo("Cefalù", 38.0337046, 14.017443599999979, 0, null), new SpotInfo("Cinisi", 38.155087, 13.108809699999938, 0, null), new SpotInfo("Fontane", 36.9650012, 15.21489500000007, 0, null), new SpotInfo("Marsala", 37.79804499999999, 12.437015599999995, 0, null), new SpotInfo("Mazara", 37.6550933, 12.589961499999959, 0, null), new SpotInfo("Messina", 38.1938137, 15.55401519999998, 0, null), new SpotInfo("Palermo", 38.1156879, 13.361267099999964, 0, null), new SpotInfo("Pantelleria", 36.8282207, 11.940496400000029, 0, null), new SpotInfo("Portopalo", 36.674213, 15.129370999999992, 0, null), new SpotInfo("Sciacca", 37.5085148, 13.08163049999996, 0, null), new SpotInfo("Trappeto", 38.068732, 13.042513900000017, 0, null), new SpotInfo("Villagrazia", 38.0782795, 13.33369479999999, 0, null)));
        DICT.put("Puglia", Arrays.asList(new SpotInfo("Bari", 41.1171432, 16.871871499999997, 0, null), new SpotInfo("Brindisi", 40.6327278, 17.941761600000063, 0, null), new SpotInfo("Capitolo", 40.9118645, 17.342013500000007, 0, null), new SpotInfo("Litoranea", 39.8427077, 18.21691899999996, 0, null), new SpotInfo("Torretta a Mare", 40.3324795, 17.4033637, 0, null), new SpotInfo("Monopoli", 40.9525166, 17.298555899999997, 0, null), new SpotInfo("Otranto", 40.1438978, 18.49116779999997, 0, null), new SpotInfo("Lesina Marina", 41.9129844, 15.334154799999965, 0, null), new SpotInfo("Trani", 41.2774857, 16.417833400000063, 0, null), new SpotInfo("Vieste", 41.8824764, 16.176748900000007, 0, null)));
        DICT.put("Molise", Arrays.asList(new SpotInfo("Campomarino", 41.9592663, 15.051268199999981, 0, null), new SpotInfo("Termoli", 42.0005331, 14.995283900000004, 0, null)));
        DICT.put("Abruzzo", Arrays.asList(new SpotInfo("Fossacesia", 42.24955850000001, 14.510912500000018, 0, null), new SpotInfo("Giulianova", 42.7538138, 13.96650509999995, 0, null), new SpotInfo("Ortona", 42.3522441, 14.40281129999994, 0, null), new SpotInfo("Tortoreto", 42.80327219999999, 13.918436499999984, 0, null), new SpotInfo("Vasto", 42.0993726, 14.720308499999987, 0, null)));
        DICT.put("Marche", Arrays.asList(new SpotInfo("Fano", 43.8398164, 13.019420099999934, 0, null), new SpotInfo("Fermo", 43.1588734, 13.720088400000009, 0, null), new SpotInfo("Grottammare", 42.9853425, 13.8683671, 0, null), new SpotInfo("Portonovo", 43.5611908, 13.599936999999954, 0, null), new SpotInfo("Senigallia", 43.7197926, 13.215222400000016, 0, null), new SpotInfo("Sirolo", 43.5191009, 13.621703400000001, 0, null), new SpotInfo("Torrette", 43.6134637, 13.432392899999968, 0, null)));
        DICT.put("Emilia", Arrays.asList(new SpotInfo("Cattolica", 43.963318, 12.738431799999944, 0, null), new SpotInfo("Cervia", 44.26354920000001, 12.347682200000008, 0, null), new SpotInfo("Cesenatico", 44.200847, 12.405202299999928, 0, null)));
        DICT.put("Veneto", Arrays.asList(new SpotInfo("Boccasette", 44.9968641, 12.427075100000025, 0, null), new SpotInfo("Caorle", 45.6030238, 12.885958899999991, 0, null), new SpotInfo("Cavallino", 45.4746418, 12.548519199999987, 0, null), new SpotInfo("Jesolo", 45.5334198, 12.643849799999998, 0, null), new SpotInfo("Sottomarina", 45.2148203, 12.292948499999966, 0, null)));
        DICT.put("Friuli", Arrays.asList(new SpotInfo("Lignano", 45.6910118, 13.146170200000029, 0, null)));
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
                nameList.add(elem.getNameSpot());
            }

            return nameList;
        }

        return null;
    }

}
