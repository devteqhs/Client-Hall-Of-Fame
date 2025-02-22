package net.minecraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemWritableBook extends Item {
   public ItemWritableBook() {
      this.setMaxStackSize(1);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
      playerIn.displayGUIBook(itemStackIn);
      playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return itemStackIn;
   }

   public static boolean isNBTValid(NBTTagCompound nbt) {
      if (nbt == null) {
         return true;
      } else if (!nbt.hasKey("pages", 9)) {
         return true;
      } else {
         NBTTagList nbttaglist = nbt.getTagList("pages", 8);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            String s = nbttaglist.getStringTagAt(i);
            if (s == null) {
               return true;
            }

            if (s.length() > 32767) {
               return true;
            }
         }

         return false;
      }
   }
}
