package de.Ste3et_C0st.ProtectionLib.main;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.worldcretornica.plotme_core.Plot;
import com.worldcretornica.plotme_core.PlotMeCoreManager;
import com.worldcretornica.plotme_core.api.ILocation;
import com.worldcretornica.plotme_core.bukkit.api.BukkitLocation;

public class fPlotMe {

	Player p;
	Location loc;
	public fPlotMe(Location loc, Player p){
		this.p = p;
		this.loc = loc;
	}
	
	public boolean canBuild(Plugin p){
		if(p==null){return true;}
		PlotMeCoreManager manager = PlotMeCoreManager.getInstance();
		ILocation loc = new BukkitLocation(this.loc);
		if(!manager.isPlotWorld(loc)){return true;}
		String id = manager.getPlotId(loc);
		if(id==null) return false;
		Plot plot = manager.getPlotById(id, loc.getWorld());
		if(plot==null) return false;
		if(plot.isAllowed(this.p.getUniqueId())) return true;
		if(plot.getOwnerId().equals(this.p.getUniqueId())) return true;
		return false;
	}
	
	public boolean isOwner(Plugin p){
		if(p==null){return true;}
		PlotMeCoreManager manager = PlotMeCoreManager.getInstance();
		ILocation loc = new BukkitLocation(this.loc);
		if(!manager.isPlotWorld(loc)){return true;}
		String id = manager.getPlotId(loc);
		if(id==null) return false;
		Plot plot = manager.getPlotById(id, loc.getWorld());
		if(plot==null) return false;
		if(plot.getOwnerId().equals(this.p.getUniqueId())) return true;
		return false;
	}
}