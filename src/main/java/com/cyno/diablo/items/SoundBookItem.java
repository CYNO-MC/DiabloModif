package com.cyno.diablo.items;

import com.cyno.diablo.client.screen.SoundBookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.gui.screen.WinGameScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class SoundBookItem extends ItemBase {
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        setContents(stack);

        Minecraft.getInstance().displayGuiScreen(new SoundBookScreen(new ReadBookScreen.WrittenBookInfo(stack)));

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void setContents(ItemStack stack){
        CompoundNBT tag = stack.getTag();
        if (tag == null) tag = new CompoundNBT();

        tag.putString("author", getAuthor());
        tag.putString("title", getTitle());
        tag.putBoolean("resolved", true);

        ListNBT pages = getPages();
        tag.put("pages", pages);

        stack.setTag(tag);
    }

    private String getAuthor() {
        return "Cyno";
    }

    private String getTitle() {
        return "Test Book";
    }

    private ListNBT getPages(){
        ListNBT pages = new ListNBT();

        addPage("test page 1", pages);
        addPage("test page 2", pages);
        addPage("test page 3", pages);
        addPage("test page 4", pages);

        return pages;
    }

    private void addPage(String content, ListNBT pages){
        INBT page = (INBT) StringNBT.valueOf("{\"text\": \"" + content + "\"}");
        // INBT page = (INBT) StringNBT.valueOf(ITextComponent.Serializer.toJson(itextcomponent));
        pages.add(page);
    }
}
