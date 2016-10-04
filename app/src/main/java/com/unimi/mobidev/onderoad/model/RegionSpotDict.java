package com.unimi.mobidev.onderoad.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Pc-Utente on 01/10/2016.
 */

public class RegionSpotDict {

    private static final TreeMap<String, List<SpotInfo>> DICT;

    static {
        DICT = new TreeMap<>();
        DICT.put("Abruzzo", Arrays.asList(new SpotInfo("Fossacesia", new LatLng(42.24955850000001, 14.510912500000018), 0, null), new SpotInfo("Giulianova", new LatLng(42.7538138, 13.96650509999995), 0, null), new SpotInfo("Ortona", new LatLng(42.3522441, 14.40281129999994), 0, null), new SpotInfo("Tortoreto", new LatLng(42.80327219999999, 13.918436499999984), 0, null), new SpotInfo("Vasto", new LatLng(42.0993726, 14.720308499999987), 0, null)));
        DICT.put("Calabria", Arrays.asList(new SpotInfo("Botricello", new LatLng(38.9348164, 16.8554977), 0, null), new SpotInfo("Caminia", new LatLng(38.7450242, 16.556197399999974), 0, null), new SpotInfo("Cittadella", new LatLng(39.5460297, 15.882038899999998), 0, null), new SpotInfo("Cropani", new LatLng(38.96781259999999, 16.781420799999978), 0, null), new SpotInfo("Crotone", new LatLng(39.0807932, 17.12711020000006), 0, null), new SpotInfo("Galati", new LatLng(37.9318063, 16.06800640000006), 0, null), new SpotInfo("Falerna Marina", new LatLng(39.0038688, 16.130160199999978), 0, null), new SpotInfo("Nocera Scalo", new LatLng(39.0156941, 16.118399899999986), 0, null), new SpotInfo("Pellaro", new LatLng(38.0185547, 15.642718899999977), 0, null), new SpotInfo("Pizzo", new LatLng(38.7337855, 16.162892100000022), 0, null), new SpotInfo("Punta", new LatLng(38.7188142, 16.05667010000002), 0, null), new SpotInfo("Siderno", new LatLng(38.2693662, 16.298308499999962), 0, null), new SpotInfo("Simeri Mare", new LatLng(38.86560679999999, 16.69766549999997), 0, null), new SpotInfo("Squillace", new LatLng(38.7787519, 16.569456100000025), 0, null), new SpotInfo("Zambrone", new LatLng(38.7054031, 15.969562200000041), 0, null)));
        DICT.put("Campania", Arrays.asList(new SpotInfo("Acciaroli", new LatLng(40.1762064, 15.03262989999996), 0, null), new SpotInfo("Agropoli", new LatLng(40.3486827, 14.991219499999943), 0, null), new SpotInfo("Palinuro", new LatLng(40.03930800000001, 15.290936399999964), 0, null), new SpotInfo("Pozzuoli", new LatLng(40.8459469, 14.0932861), 0, null), new SpotInfo("Procida", new LatLng(40.7622633, 14.030954500000007), 0, null), new SpotInfo("Salerno", new LatLng(40.68244079999999, 14.76809609999998), 0, null)));
        DICT.put("Lazio", Arrays.asList(new SpotInfo("Anzio", new LatLng(41.4495955, 12.619725300000027), 0, null), new SpotInfo("Campascolano", new LatLng(41.6555633, 12.42802800000004), 0, null), new SpotInfo("Marina di Cerveteri", new LatLng(41.9803955, 12.05040699999995), 0, null), new SpotInfo("Civitavecchia", new LatLng(42.0924239, 11.795413199999984), 0, null), new SpotInfo("Fiumicino", new LatLng(41.7735409, 12.239711700000043), 0, null), new SpotInfo("Focene", new LatLng(41.817242, 12.211542099999974), 0, null), new SpotInfo("Fregene", new LatLng(41.8558992, 12.198098999999957), 0, null), new SpotInfo("Gaeta", new LatLng(41.2107299, 13.571428500000025), 0, null), new SpotInfo("Ladispoli", new LatLng(41.9498965, 12.076104499999929), 0, null), new SpotInfo("Latina", new LatLng(41.4675671, 12.903596500000049), 0, null), new SpotInfo("Lavinio", new LatLng(41.4997397, 12.588638899999978), 0, null), new SpotInfo("Montalto Marina", new LatLng(42.3220694, 11.586134600000037), 0, null), new SpotInfo("Nettuno", new LatLng(41.4577305, 12.665491400000064), 0, null), new SpotInfo("Ostia", new LatLng(41.7311182, 12.286269400000037), 0, null), new SpotInfo("Passoscuro", new LatLng(41.8986149, 12.165179800000033), 0, null), new SpotInfo("Sabaudia", new LatLng(41.3015823, 13.027575800000022), 0, null), new SpotInfo("Sperlonga", new LatLng(41.2636741, 13.427141399999982), 0, null), new SpotInfo("Terracina", new LatLng(41.2963728, 13.233265699999947), 0, null), new SpotInfo("Torvaianica", new LatLng(41.6208388, 12.46263870000007), 0, null)));
        DICT.put("Liguria", Arrays.asList(new SpotInfo("Alassio", new LatLng(44.014336, 8.181174100000021), 0, null), new SpotInfo("Albenga", new LatLng(44.0494254, 8.215611299999978), 0, null), new SpotInfo("Andora", new LatLng(43.9848226, 8.130599200000006), 0, null), new SpotInfo("Arenzano", new LatLng(44.4058612, 8.686016699999982), 0, null), new SpotInfo("Bocca", new LatLng(44.0536506, 9.976260700000012), 0, null), new SpotInfo("Bogliasco", new LatLng(44.3792927, 9.064765299999976), 0, null), new SpotInfo("Camogli", new LatLng(44.3542792, 9.149817799999937), 0, null), new SpotInfo("Celle", new LatLng(52.6086266, 10.008386400000063), 0, null), new SpotInfo("Chiavari", new LatLng(44.3168416, 9.319982200000027), 0, null), new SpotInfo("Diano", new LatLng(43.9088641, 8.082802399999991), 0, null), new SpotInfo("Finale", new LatLng(44.431648, 8.791339999999991), 0, null), new SpotInfo("Genova", new LatLng(44.4056499, 8.946255999999948), 0, null), new SpotInfo("Imperia", new LatLng(43.8896857, 8.039516999999933), 0, null), new SpotInfo("Lerici", new LatLng(44.0756332, 9.916933699999959), 0, null), new SpotInfo("Levanto", new LatLng(44.1686141, 9.610654000000068), 0, null), new SpotInfo("Loano", new LatLng(44.13064079999999, 8.260967100000016), 0, null), new SpotInfo("Moneglia", new LatLng(44.239147, 9.488617699999963), 0, null), new SpotInfo("Pietra", new LatLng(44.1502851, 8.283452300000022), 0, null), new SpotInfo("Portofino", new LatLng(44.3031559, 9.209787900000038), 0, null), new SpotInfo("Portovenere", new LatLng(44.0541257, 9.836628000000019), 0, null), new SpotInfo("Rapallo", new LatLng(44.3498211, 9.23388890000001), 0, null), new SpotInfo("Recco", new LatLng(44.3614219, 9.143744500000025), 0, null), new SpotInfo("Sanremo", new LatLng(43.81596709999999, 7.7760567000000265), 0, null), new SpotInfo("Sarzana", new LatLng(44.111424, 9.963121499999943), 0, null), new SpotInfo("Savona", new LatLng(44.2975603, 8.464500000000044), 0, null), new SpotInfo("Sestri", new LatLng(44.2763654, 9.400828100000012), 0, null), new SpotInfo("Vado", new LatLng(44.2691047, 8.439781900000071), 0, null), new SpotInfo("Varazze", new LatLng(44.3594336, 8.577312600000027), 0, null), new SpotInfo("Ventimiglia", new LatLng(43.7912366, 7.607586399999946), 0, null), new SpotInfo("Zoagli", new LatLng(44.3364754, 9.268170999999938), 0, null)));
        DICT.put("Molise", Arrays.asList(new SpotInfo("Campomarino", new LatLng(41.9592663, 15.051268199999981), 0, null), new SpotInfo("Termoli", new LatLng(42.0005331, 14.995283900000004), 0, null)));
        DICT.put("Puglia", Arrays.asList(new SpotInfo("Bari", new LatLng(41.1171432, 16.871871499999997), 0, null), new SpotInfo("Brindisi", new LatLng(40.6327278, 17.941761600000063), 0, null), new SpotInfo("Capitolo", new LatLng(40.9118645, 17.342013500000007), 0, null), new SpotInfo("Litoranea", new LatLng(39.8427077, 18.21691899999996), 0, null), new SpotInfo("Torretta a Mare", new LatLng(40.3324795, 17.4033637), 0, null), new SpotInfo("Monopoli", new LatLng(40.9525166, 17.298555899999997), 0, null), new SpotInfo("Otranto", new LatLng(40.1438978, 18.49116779999997), 0, null), new SpotInfo("Otranto", new LatLng(40.1438978, 18.49116779999997), 0, null), new SpotInfo("Lesina Marina", new LatLng(41.9129844, 15.334154799999965), 0, null), new SpotInfo("Trani", new LatLng(41.2774857, 16.417833400000063), 0, null), new SpotInfo("Vieste", new LatLng(41.8824764, 16.176748900000007), 0, null)));
        DICT.put("Sardegna", Arrays.asList(new SpotInfo("Vignola Mare", new LatLng(41.1270014, 9.063050999999973), 0, null), new SpotInfo("Alghero", new LatLng(40.5579517, 8.319294899999932), 0, null), new SpotInfo("Badesi Mare", new LatLng(40.971573, 8.863142000000039), 0, null), new SpotInfo("Buggerru", new LatLng(39.399722, 8.402429999999981), 0, null), new SpotInfo("Cagliari", new LatLng(39.2136846, 9.171179499999994), 0, null), new SpotInfo("Calasetta", new LatLng(39.10979409999999, 8.369288900000015), 0, null), new SpotInfo("Capitana", new LatLng(39.2093022, 9.310803999999962), 0, null), new SpotInfo("Museddu", new LatLng(39.7762992, 9.660178299999984), 0, null), new SpotInfo("Sant'Elmo", new LatLng(39.2151, 9.565261700000065), 0, null), new SpotInfo("Chia", new LatLng(38.90813190000001, 8.876944900000012), 0, null), new SpotInfo("Costa Rei", new LatLng(39.2500991, 9.570129599999973), 0, null), new SpotInfo("Santa Margherita di Pula", new LatLng(38.930935, 8.925914199999966), 0, null), new SpotInfo("Oristano", new LatLng(39.8676904, 8.558367599999997), 0, null), new SpotInfo("Stintino", new LatLng(40.9401332, 8.223588899999982), 0, null), new SpotInfo("Cala Caterina", new LatLng(39.1089509, 9.508258100000035), 0, null)));
        DICT.put("Sicilia", Arrays.asList(new SpotInfo("Agrigento", new LatLng(37.2871465, 13.557429599999978), 0, null), new SpotInfo("Aspra", new LatLng(38.104746, 13.498790999999983), 0, null), new SpotInfo("Avola", new LatLng(36.9095319, 15.13488910000001), 0, null), new SpotInfo("Catania", new LatLng(37.5078772, 15.083030399999984), 0, null), new SpotInfo("Cefalù", new LatLng(38.0337046, 14.017443599999979), 0, null), new SpotInfo("Cinisi", new LatLng(38.155087, 13.108809699999938), 0, null), new SpotInfo("Fontane", new LatLng(36.9650012, 15.21489500000007), 0, null), new SpotInfo("Marsala", new LatLng(37.79804499999999, 12.437015599999995), 0, null), new SpotInfo("Mazara", new LatLng(37.6550933, 12.589961499999959), 0, null), new SpotInfo("Messina", new LatLng(38.1938137, 15.55401519999998), 0, null), new SpotInfo("Palermo", new LatLng(38.1156879, 13.361267099999964), 0, null), new SpotInfo("Pantelleria", new LatLng(36.8282207, 11.940496400000029), 0, null), new SpotInfo("Portopalo", new LatLng(36.674213, 15.129370999999992), 0, null), new SpotInfo("Sciacca", new LatLng(37.5085148, 13.08163049999996), 0, null), new SpotInfo("Trappeto", new LatLng(38.068732, 13.042513900000017), 0, null), new SpotInfo("Villagrazia", new LatLng(38.0782795, 13.33369479999999), 0, null)));
        DICT.put("Toscana", Arrays.asList(new SpotInfo("Forte", new LatLng(43.95796170000001, 10.167279199999939), 0, null), new SpotInfo("Livorno", new LatLng(43.548473, 10.310567399999968), 0, null), new SpotInfo("Marina di Carrara", new LatLng(44.0359679, 10.043825599999991), 0, null), new SpotInfo("Marina di Massa", new LatLng(44.0273656, 10.068718200000035), 0, null), new SpotInfo("Marina di Pietrasanta", new LatLng(43.9306952, 10.206750100000022), 0, null), new SpotInfo("Piombino", new LatLng(42.9256335, 10.525889099999972), 0, null), new SpotInfo("Ansedonia", new LatLng(42.40812469999999, 11.28329629999996), 0, null), new SpotInfo("Antignano", new LatLng(43.498668, 10.326764000000026), 0, null), new SpotInfo("Baratti", new LatLng(42.9990764, 10.517120400000067), 0, null), new SpotInfo("Capraia", new LatLng(43.7350466, 11.015325399999938), 0, null), new SpotInfo("Castiglioncello", new LatLng(43.4047359, 10.418585500000063), 0, null), new SpotInfo("Cinquale", new LatLng(43.9828071, 10.153797199999985), 0, null), new SpotInfo("Marina di Grosseto", new LatLng(42.7151275, 10.984806400000025), 0, null), new SpotInfo("Lido Di Camaiore", new LatLng(43.9050586, 10.229172500000004), 0, null), new SpotInfo("Pratoranieri", new LatLng(42.9406934, 10.713646599999947), 0, null), new SpotInfo("Quercianella", new LatLng(43.4592601, 10.36780120000003), 0, null), new SpotInfo("Rosignano", new LatLng(43.387968, 10.438226600000007), 0, null), new SpotInfo("Tirrenia", new LatLng(43.6294361, 10.29086319999999), 0, null), new SpotInfo("Viareggio", new LatLng(43.8657267, 10.2513103), 0, null)));
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
