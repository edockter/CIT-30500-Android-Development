package app.ericdock.iupui.edu.nflteams;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ericd on 2/6/2017.
 */

public class NFLTeamBucket {
    // static var containing this bucket
    private static NFLTeamBucket sNFLTeamBucket;

    // Singleton has 1 attribute:
    private List<NFLTeam> mNFLTeamList;

    // Constructor
    private NFLTeamBucket(Context context) {
        // Initialize empty list
        mNFLTeamList = new ArrayList<>();

        // Temporarily create all teams manually
        NFLTeam bills = new NFLTeam("Buffalo Bills", "bills", "bills", "AFC", "AFC East", "Ralph Wilson Stadium", "42.773611", "-78.786944");
        NFLTeam dolphins = new NFLTeam("Miami Dolphins", "dolphins", "dolphins.png", "AFC", "AFC East", "Sun Life Stadium", "25.958056", "-80.238889");
        NFLTeam patriots = new NFLTeam("New England Patriots", "patriots", "patriots.png", "AFC", "AFC East", "Gilette Stadium", "42.090944", "-71.264344");
        NFLTeam jets = new NFLTeam("New York Jets", "jets", "jets.png", "AFC", "AFC East", "Met Life Stadium", "40.813611", "-74.074444");
        NFLTeam ravens = new NFLTeam("Baltimore Ravens", "ravens", "ravens.png", "AFC", "AFC North", "M&T Bank Stadium", "39.278056", "-76.622778");
        NFLTeam bengals = new NFLTeam("Cincinnati Bengals", "bengals", "bengals.png", "AFC", "AFC North", "Paul Brown Stadium", "39.095444", "-84.516039");
        NFLTeam browns = new NFLTeam("Cleveland Browns", "browns", "browns.png", "AFC", "AFC North", "Cleveland Browns Stadium", "41.506111", "-81.699444");
        NFLTeam steelers = new NFLTeam("Pittsburgh Steelers", "steelers", "steelers.png", "AFC", "AFC North", "Heinz Field", "40.446667", "-80.015833");
        NFLTeam texans = new NFLTeam("Houston Texans", "texans", "texans.png", "AFC", "AFC South", "Reliant Stadium", "29.684722", "-95.410833");
        NFLTeam colts = new NFLTeam("Indianapolis Colts", "colts", "colts.png", "AFC", "AFC South", "Lucas Oil Stadium", "39.760056", "-86.163806");
        NFLTeam jaguars = new NFLTeam("Jacksonville Jaguars", "jaguars", "jaguars.png", "AFC", "AFC South", "EverBank Field", "30.323889", "-81.6375");
        NFLTeam titans = new NFLTeam("Tennessee Titans", "titans", "titans.png", "AFC", "AFC South", "LP Field", "36.166251", "-86.771422");
        NFLTeam broncos = new NFLTeam("Denver Broncos", "broncos", "broncos.png", "AFC", "AFC West", "Sports Authority Field", "39.743889", "-105.02");
        NFLTeam chiefs = new NFLTeam("Kansas City Chiefs", "chiefs", "chiefs.png", "AFC", "AFC West", "Arrowhead Stadium", "39.048889", "-94.483889");
        NFLTeam raiders = new NFLTeam("Oakland Raiders", "raiders", "raiders.png", "AFC", "AFC West", "O.co Stadium", "37.751667", "-122.200556");
        NFLTeam chargers = new NFLTeam("San Diego Chargers", "chargers", "chargers.png", "AFC", "AFC West", "Qualcomm Stadium", "32.783056", "-117.119444");
        NFLTeam cowboys = new NFLTeam("Dallas Cowboys", "cowboys", "cowboys.png", "NFC", "NFC East", "Cowboys Stadium", "32.747778", "-97.092778");
        NFLTeam giants = new NFLTeam("New York Giants", "giants", "giants.png", "NFC", "NFC East", "Met Life Stadium", "40.813611", "-74.074444");
        NFLTeam eagles = new NFLTeam("Philadelphia Eagles", "eagles", "eagles.png", "NFC", "NFC East", "Lincoln Financial Field", "39.900833", "-75.1675");
        NFLTeam redskins = new NFLTeam("Washington Redskins", "redskins", "redskins.png", "NFC", "NFC East", "FedEx Field", "38.907778", "-76.864444");
        NFLTeam bears = new NFLTeam("Chicago Bears", "bears", "bears.png", "NFC", "NFC North", "Soldier Field", "41.8625", "-87.616667");
        NFLTeam lions = new NFLTeam("Detroit Lions", "lions", "lions.png", "NFC", "NFC North", "Ford Field", "42.34", "-83.045556");
        NFLTeam packers = new NFLTeam("Green Bay Packers", "packers", "packers.png", "NFC", "NFC North", "Lambeau Field", "44.501389", "-88.062222");
        NFLTeam vikings = new NFLTeam("Minnesota Vikings", "vikings", "vikings.png", "NFC", "NFC North", "Mall of America Field", "44.973889", "-93.258056");
        NFLTeam falcons = new NFLTeam("Atlanta Falcons", "falcons", "falcons.png", "NFC", "NFC South", "Georgia Dome", "33.7575", "-84.400833");
        NFLTeam panthers = new NFLTeam("Carolina Panthers", "panthers", "panthers.png", "NFC", "NFC South", "Bank of America Stadium", "35.225833", "-80.852778");
        NFLTeam saints = new NFLTeam("New Orleans Saints", "saints", "saints.png", "NFC", "NFC South", "Mercedes-Benz Superdome", "29.950833", "-90.081111");
        NFLTeam buccaneers = new NFLTeam("Tampa Bay Buccaneers", "buccaneers", "buccaneers.png", "NFC", "NFC South", "Raymond James Stadium", "27.975833", "-82.503333");
        NFLTeam cardinals = new NFLTeam("Arizona Cardinals", "cardinals", "cardinals.png", "NFC", "NFC West", "University of Phoenix Stadium", "33.5275", "-112.2625");
        NFLTeam rams = new NFLTeam("Los Angeles Rams", "rams", "rams.png", "NFC", "NFC West", "LA Memorial Coliseum", "34.0138747", "-118.2903216");
        NFLTeam the49ers = new NFLTeam("San Francisco 49ers", "the49ers", "the49ers.png", "NFC", "NFC West", "Candlestick Park", "37.713611", "-122.386111");
        NFLTeam seahawks = new NFLTeam("Seattle Seahawks", "seahawks", "seahawks.png", "NFC", "NFC West", "CenturyLink Field", "47.595278", "-122.331667");

        mNFLTeamList.add(bills);
        mNFLTeamList.add(dolphins);
        mNFLTeamList.add(patriots);
        mNFLTeamList.add(jets);
        mNFLTeamList.add(ravens);
        mNFLTeamList.add(bengals);
        mNFLTeamList.add(browns);
        mNFLTeamList.add(steelers);
        mNFLTeamList.add(texans);
        mNFLTeamList.add(colts);
        mNFLTeamList.add(jaguars);
        mNFLTeamList.add(titans);
        mNFLTeamList.add(broncos);
        mNFLTeamList.add(chiefs);
        mNFLTeamList.add(raiders);
        mNFLTeamList.add(chargers);
        mNFLTeamList.add(cowboys);
        mNFLTeamList.add(giants);
        mNFLTeamList.add(eagles);
        mNFLTeamList.add(redskins);
        mNFLTeamList.add(bears);
        mNFLTeamList.add(lions);
        mNFLTeamList.add(packers);
        mNFLTeamList.add(vikings);
        mNFLTeamList.add(falcons);
        mNFLTeamList.add(panthers);
        mNFLTeamList.add(saints);
        mNFLTeamList.add(buccaneers);
        mNFLTeamList.add(cardinals);
        mNFLTeamList.add(rams);
        mNFLTeamList.add(the49ers);
        mNFLTeamList.add(seahawks);
    }

    public List<NFLTeam> getNFLTeamList() {
        return mNFLTeamList;
    }

    // Grab 1 item off of the list
    public NFLTeam getNFLTeam(UUID id) {
        //
        for (NFLTeam nflTeam:mNFLTeamList) {
            if (nflTeam.getId().equals(id)) {
                return nflTeam;
            }
        }

        return null;
    }

    // retrieve single instance of NFLTeamBucket
    public static NFLTeamBucket get(Context context) {

        if (sNFLTeamBucket == null) {
            sNFLTeamBucket = new NFLTeamBucket(context);
        }

        return sNFLTeamBucket;
    }
}