package fr.iceknith.uhc_civilization;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardDisplay {
    @SuppressWarnings({"deprecation"})
    public void update() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective objective = board.registerNewObjective("time", "score");
        objective.setDisplayName("Time left");
        objective.getScore("Time left").setScore((int) Main.timer.millis);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.setScoreboard(board);
        }
    }
}
