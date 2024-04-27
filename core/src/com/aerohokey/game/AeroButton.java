package com.aerohokey.game;

import static com.aerohokey.game.Aerohockey.SCR_WIDTH;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class AeroButton {
    String text;
    BitmapFont font;
    float x, y;
    float width, height;

    public AeroButton(String text, float x, float y, BitmapFont font) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        GlyphLayout layout = new GlyphLayout(font, text);
        width = layout.width;
        height = layout.height;
    }

    public AeroButton(String text, float y, BitmapFont font) {
        this.text = text;
        this.font = font;
        GlyphLayout layout = new GlyphLayout(font, text);
        width = layout.width;
        height = layout.height;
        x = SCR_WIDTH/2 - width/2;
        this.y = y;
    }

    boolean hit(float tx, float ty){
        return x < tx & tx < x+width & y-height < ty & ty < y;
    }

    public void setText(String text) {
        this.text = text;
        GlyphLayout layout = new GlyphLayout(font, text);
        width = layout.width;
    }
}
