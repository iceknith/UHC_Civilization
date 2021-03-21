package fr.iceknith.uhc_civilization.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"deprecation"})
public class ScoreboardDisplay {
    private final String title = "AEUGH";
    private List<ScoreboardEntry> entries = new ArrayList<>();
    private List<ScoreboardEntry> entriesLast;
    private Objective mainObjective;
    private Team mainTeam;
    private Scoreboard board;

    public ScoreboardDisplay() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();

        board = manager.getNewScoreboard();

        mainObjective = board.registerNewObjective("main", "dummy");
        mainObjective.setDisplayName(title);

        mainTeam = board.registerNewTeam("main");
        forceUpdate();
        mainObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.setScoreboard(board);
        }
    }

    @SuppressWarnings({"deprecation"})
    public void update(int i) {
        String entry = mainTeam.getEntries().toArray(new String[0])[i];
        System.out.println("Removing entry: " + entry);
        board.resetScores(entry);
        mainTeam.removeEntry(mainTeam.getEntries().toArray(new String[0])[i]);
        mainTeam.addPlayer(entries.get(i));
        mainObjective.getScore(entries.get(i)).setScore(i);

    }

    public void update(ScoreboardEntry entry) {
        update(entries.indexOf(entry));
    }

    public void forceUpdate() {
        for (int i = 0; i < entries.size(); i++) {
            mainTeam.addPlayer(entries.get(i));
            mainObjective.getScore(entries.get(i)).setScore(i);
        }
    }

    public void addEntry(ScoreboardEntry entry) {
        entry.index = entries.size();
        entries.add(entry);
        forceUpdate();
    }

    public void updateAll() {
        for (int i = 0; i < entries.size(); i++) {
            update(i);
        }
    }
}
