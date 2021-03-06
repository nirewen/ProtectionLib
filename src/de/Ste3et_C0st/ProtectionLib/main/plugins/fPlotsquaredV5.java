package de.Ste3et_C0st.ProtectionLib.main.plugins;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.plotsquared.core.events.PlotClearEvent;
import com.plotsquared.core.events.PlotDeleteEvent;
import com.plotsquared.core.location.Location;
import com.plotsquared.core.plot.Plot;
import de.Ste3et_C0st.ProtectionLib.events.RegionClearEvent;
import de.Ste3et_C0st.ProtectionLib.main.ProtectionLib;
import de.Ste3et_C0st.ProtectionLib.main.protectionObj;

public class fPlotsquaredV5 extends protectionObj implements Listener{

	public fPlotsquaredV5(Plugin pl) {
		super(pl);
		Bukkit.getPluginManager().registerEvents(this, ProtectionLib.getInstance());
	}
	
	@EventHandler
	private void onDelete(PlotClearEvent clearEvent) {
		List<Location> locationList = clearEvent.getPlot().getAllCorners();
		Location plotLocMin = locationList.get(0);
		Location plotLocMax = locationList.get(2);
		if(Objects.nonNull(plotLocMin) && Objects.nonNull(plotLocMax)) {
			World world = Bukkit.getWorld(plotLocMin.getWorld());
			org.bukkit.Location locationMin = new org.bukkit.Location(world, plotLocMin.getX(), plotLocMin.getY(), plotLocMin.getZ());
			org.bukkit.Location locationMax = new org.bukkit.Location(world, plotLocMax.getX(), plotLocMax.getY(), plotLocMax.getZ());
			RegionClearEvent event = new RegionClearEvent(locationMin, locationMax);
			Bukkit.getPluginManager().callEvent(event);
		}
	}
	
	@EventHandler
	private void onDelete(PlotDeleteEvent clearEvent) {
		List<Location> locationList = clearEvent.getPlot().getAllCorners();
		Location plotLocMin = locationList.get(0);
		Location plotLocMax = locationList.get(2);
		if(Objects.nonNull(plotLocMin) && Objects.nonNull(plotLocMax)) {
			World world = Bukkit.getWorld(plotLocMin.getWorld());
			org.bukkit.Location locationMin = new org.bukkit.Location(world, plotLocMin.getX(), plotLocMin.getY(), plotLocMin.getZ());
			org.bukkit.Location locationMax = new org.bukkit.Location(world, plotLocMax.getX(), plotLocMax.getY(), plotLocMax.getZ());
			RegionClearEvent event = new RegionClearEvent(locationMin, locationMax);
			Bukkit.getPluginManager().callEvent(event);
		}
	}

	@Override
	public boolean canBuild(Player player, org.bukkit.Location loc) {
		Location location = new Location(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		if(location.isPlotArea()) {
			Plot plot = location.getPlot();
			UUID uuid = player.getUniqueId();
			if(Objects.nonNull(plot)) {
				return plot.isAdded(uuid) || plot.isOwner(uuid);
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean isOwner(Player player, org.bukkit.Location loc) {
		Location location = new Location(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		Plot plot = location.getPlot();
		if(Objects.nonNull(plot)) {
			return plot.isOwner(player.getUniqueId());
		}
		return location.isPlotRoad();
	}

}
