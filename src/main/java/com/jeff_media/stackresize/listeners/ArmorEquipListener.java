package com.jeff_media.stackresize.listeners;

import com.jeff_media.armorequipevent.ArmorEquipEvent;
import com.jeff_media.stackresize.StackResize;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ArmorEquipListener implements Listener {

    private static final StackResize main = StackResize.getInstance();

    @EventHandler
    public void onEquip(ArmorEquipEvent event) {
        ItemStack newArmor = event.getNewArmorPiece();
        if(newArmor == null) return;
        if(!main.isChanged(newArmor.getType())) return;
        if(newArmor.getAmount() <= 1) return;
        ItemStack justOne = newArmor.clone();
        justOne.setAmount(1);
        Bukkit.getScheduler().runTaskLater(main,() -> event.getPlayer().updateInventory(),1L);
        event.setCancelled(true);
        if(event.getOldArmorPiece() != null) return;
        event.getPlayer().getInventory().remove(event.getNewArmorPiece());
        event.setNewArmorPiece(justOne);
    }

}
