package net.minecraft.world;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

public class NextTickListEntry implements Comparable<NextTickListEntry> {
   private static long nextTickEntryID;
   private final Block block;
   public final BlockPos position;
   public long scheduledTime;
   public int priority;
   private final long tickEntryID;

   public NextTickListEntry(BlockPos p_i45745_1_, Block p_i45745_2_) {
      this.tickEntryID = (long)(nextTickEntryID++);
      this.position = p_i45745_1_;
      this.block = p_i45745_2_;
   }

   @Override
   public boolean equals(Object p_equals_1_) {
      if (!(p_equals_1_ instanceof NextTickListEntry)) {
         return false;
      } else {
         NextTickListEntry nextticklistentry = (NextTickListEntry)p_equals_1_;
         return this.position.equals(nextticklistentry.position) && Block.isEqualTo(this.block, nextticklistentry.block);
      }
   }

   @Override
   public int hashCode() {
      return this.position.hashCode();
   }

   public void setScheduledTime(long p_77176_1_) {
      this.scheduledTime = p_77176_1_;
   }

   public void setPriority(int p_82753_1_) {
      this.priority = p_82753_1_;
   }

   public int compareTo(NextTickListEntry p_compareTo_1_) {
      return this.scheduledTime < p_compareTo_1_.scheduledTime
         ? -1
         : (
            this.scheduledTime > p_compareTo_1_.scheduledTime
               ? 1
               : (
                  this.priority != p_compareTo_1_.priority
                     ? this.priority - p_compareTo_1_.priority
                     : Long.compare(this.tickEntryID, p_compareTo_1_.tickEntryID)
               )
         );
   }

   @Override
   public String toString() {
      return Block.getIdFromBlock(this.block) + ": " + this.position + ", " + this.scheduledTime + ", " + this.priority + ", " + this.tickEntryID;
   }

   public Block getBlock() {
      return this.block;
   }
}
