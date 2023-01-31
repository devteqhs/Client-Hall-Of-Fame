package net.minecraft.client.gui;

import java.io.IOException;
import net.minecraft.client.resources.I18n;

public class GuiConfirmOpenLink extends GuiYesNo {
   private final String openLinkWarning;
   private final String copyLinkButtonText;
   private final String linkText;
   private boolean showSecurityWarning = true;

   public GuiConfirmOpenLink(GuiYesNoCallback p_i1084_1_, String linkTextIn, int p_i1084_3_, boolean p_i1084_4_) {
      super(p_i1084_1_, I18n.format(p_i1084_4_ ? "chat.link.confirmTrusted" : "chat.link.confirm"), linkTextIn, p_i1084_3_);
      this.confirmButtonText = I18n.format(p_i1084_4_ ? "chat.link.open" : "gui.yes");
      this.cancelButtonText = I18n.format(p_i1084_4_ ? "gui.cancel" : "gui.no");
      this.copyLinkButtonText = I18n.format("chat.copy");
      this.openLinkWarning = I18n.format("chat.link.warning");
      this.linkText = linkTextIn;
   }

   @Override
   public void initGui() {
      super.initGui();
      this.buttonList.clear();
      this.buttonList.add(new GuiButton(0, width / 2 - 50 - 105, height / 6 + 96, 100, 20, this.confirmButtonText));
      this.buttonList.add(new GuiButton(2, width / 2 - 50, height / 6 + 96, 100, 20, this.copyLinkButtonText));
      this.buttonList.add(new GuiButton(1, width / 2 - 50 + 105, height / 6 + 96, 100, 20, this.cancelButtonText));
   }

   @Override
   protected void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 2) {
         this.copyLinkToClipboard();
      }

      this.parentScreen.confirmClicked(button.id == 0, this.parentButtonClickedId);
   }

   public void copyLinkToClipboard() {
      setClipboardString(this.linkText);
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (this.showSecurityWarning) {
         this.drawCenteredString(this.fontRendererObj, this.openLinkWarning, width / 2, 110, 16764108);
      }
   }

   public void disableSecurityWarning() {
      this.showSecurityWarning = false;
   }
}
