/*
 * Copyright (C) 2015 Dieter J Kybelksties
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * @date: 2015-12-16
 * @author: Dieter J Kybelksties
 */
package com.kybelksties.gui.controls;

import com.kybelksties.gui.ColorUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import org.openide.util.NbBundle;

/**
 * A combo-box extension specialised for gradient types.
 *
 * @author kybelksd
 */
public class GradientTypeComboBox extends JComboBox
{

    private static final Class CLAZZ = GradientTypeComboBox.class;
    private static final String CLASS_NAME = CLAZZ.getName();
    private static final Logger LOGGER = Logger.getLogger(CLASS_NAME);

    HashMap<String, GradientIcon> name2Icon = new HashMap<>();

    /**
     * Default construct.
     */
    public GradientTypeComboBox()
    {
        addGradientItem(GradientType.FOUR_COLOR_RECTANGULAR);
        addGradientItem(GradientType.RANDOM_RASTER);
        addGradientItem(GradientType.TOP_TO_BOTTOM);
        addGradientItem(GradientType.LEFT_TO_RIGHT);
        addGradientItem(GradientType.DIAGONAL_LEFT_TOP_TO_RIGHT_BOTTOM);
        addGradientItem(GradientType.DIAGONAL_RIGHT_TOP_TO_LEFT_BOTTOM);
        addGradientItem(GradientType.CIRCULAR);
        setRenderer(new ComboBoxRenderer());
    }

    GradientIcon getIcon(String iconName)
    {
        return name2Icon.get(iconName);
    }

    void selectString(String text)
    {
        for (int i = 0; i < getItemCount(); i++)
        {
            if (getItemAt(i).toString().equals(text))
            {
                setSelectedItem(text);
            }
        }
    }

    /**
     * Add an item to the drop-down.
     *
     * @param gradientType the new gradient type
     */
    public final void addGradientItem(GradientType gradientType)
    {
        GradientIcon icon = new GradientIcon(gradientType);
        name2Icon.put(gradientType.toString(), icon);
        addItem(gradientType);

    }

    /**
     * Retrieve an array of descriptive strings of the combo-box choices.
     *
     * @return a string array
     */
    public String[] getChoicesAsStrings()
    {
        ArrayList<String> strList = new ArrayList<>();
        for (int i = 0; i < this.getItemCount(); i++)
        {
            strList.add(getItemAt(i).toString());
        }
        return (String[]) strList.toArray();

    }

    /**
     * Icons for drawing in the combo box.
     */
    public static class GradientIcon
            implements Icon
    {

        GradientType gradientType;

        GradientIcon(GradientType gradientType)
        {
            this.gradientType = gradientType;
        }

        @Override
        public final int getIconWidth()
        {
            return 60;
        }

        @Override
        public final int getIconHeight()
        {
            return 40;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y)
        {

            g.setColor(Color.black);
            Graphics2D g2 = (Graphics2D) g;
            switch (gradientType)
            {
                case TOP_TO_BOTTOM:
                    g2.setPaint(
                            new LinearGradientPaint(
                                    new Point(0, 0),
                                    new Point(0, getIconHeight()),
                                    new float[]
                                    {
                                        0.0F,
                                        0.15F,
                                        0.30F,
                                        0.45F,
                                        0.6F,
                                        0.75F,
                                        0.9F
                                    },
                                    new Color[]
                                    {
                                        new Color(200, 0, 200),
                                        Color.BLUE,
                                        Color.CYAN,
                                        Color.GREEN,
                                        Color.YELLOW,
                                        Color.ORANGE,
                                        Color.RED
                                    },
                                    MultipleGradientPaint.CycleMethod.REFLECT
                            )
                    );
                    break;
                case LEFT_TO_RIGHT:
                    g2.setPaint(
                            new LinearGradientPaint(
                                    new Point(0, 0),
                                    new Point(getIconWidth(), 0),
                                    new float[]
                                    {
                                        0.0F,
                                        0.15F,
                                        0.30F,
                                        0.45F,
                                        0.6F,
                                        0.75F,
                                        0.9F
                                    },
                                    new Color[]
                                    {
                                        new Color(200, 0, 200),
                                        Color.BLUE,
                                        Color.CYAN,
                                        Color.GREEN,
                                        Color.YELLOW,
                                        Color.ORANGE,
                                        Color.RED
                                    },
                                    MultipleGradientPaint.CycleMethod.REFLECT
                            )
                    );
                    break;
                case DIAGONAL_LEFT_TOP_TO_RIGHT_BOTTOM:
                    g2.setPaint(
                            new LinearGradientPaint(
                                    new Point(0, 0),
                                    new Point(getIconWidth(), getIconHeight()),
                                    new float[]
                                    {
                                        0.0F,
                                        0.15F,
                                        0.30F,
                                        0.45F,
                                        0.6F,
                                        0.75F,
                                        0.9F
                                    },
                                    new Color[]
                                    {
                                        new Color(200, 0, 200),
                                        Color.BLUE,
                                        Color.CYAN,
                                        Color.GREEN,
                                        Color.YELLOW,
                                        Color.ORANGE,
                                        Color.RED
                                    },
                                    MultipleGradientPaint.CycleMethod.REFLECT
                            )
                    );
                    break;
                case DIAGONAL_RIGHT_TOP_TO_LEFT_BOTTOM:
                    g2.setPaint(
                            new LinearGradientPaint(
                                    new Point(getIconWidth(), 0),
                                    new Point(0, getIconHeight()),
                                    new float[]
                                    {
                                        0.0F,
                                        0.15F,
                                        0.30F,
                                        0.45F,
                                        0.6F,
                                        0.75F,
                                        0.9F
                                    },
                                    new Color[]
                                    {
                                        new Color(200, 0, 200),
                                        Color.BLUE,
                                        Color.CYAN,
                                        Color.GREEN,
                                        Color.YELLOW,
                                        Color.ORANGE,
                                        Color.RED
                                    },
                                    MultipleGradientPaint.CycleMethod.REFLECT
                            )
                    );
                    break;
                case CIRCULAR:
                    g2.setPaint(
                            new RadialGradientPaint(
                                    new Point(
                                            getIconWidth() / 3,
                                            getIconHeight() / 3),
                                    getIconWidth() / 3.0F,
                                    new Point(
                                            getIconWidth() / 3,
                                            getIconHeight() / 2),
                                    new float[]
                                    {
                                        0.0F,
                                        0.15F,
                                        0.30F,
                                        0.45F,
                                        0.6F,
                                        0.75F,
                                        0.9F
                                    },
                                    new Color[]
                                    {
                                        new Color(200, 0, 200),
                                        Color.BLUE,
                                        Color.CYAN,
                                        Color.GREEN,
                                        Color.YELLOW,
                                        Color.ORANGE,
                                        Color.RED
                                    },
                                    MultipleGradientPaint.CycleMethod.REFLECT));

                    break;
                case FOUR_COLOR_RECTANGULAR:
                    g2.setPaint(
                            new ColorUtils.FourColorGradientPaint(
                                    new Point(10, 10),
                                    new Point(getIconWidth(),
                                              getIconHeight()),
                                    Color.RED,
                                    Color.GREEN,
                                    Color.BLUE,
                                    Color.YELLOW));

                    break;
                case RANDOM_RASTER:
                    g2.setPaint(randomGridGradientPaint);

            }
            g.fillRect(0, 0, getIconWidth(), getIconHeight());
        }
        private final ColorUtils.RandomGridGradientPaint randomGridGradientPaint =
                                                         new ColorUtils.RandomGridGradientPaint(
                                                                 new Point(0, 0),
                                                                 new Point(
                                                                         getIconWidth(),
                                                                         getIconHeight()),
                                                                 getIconWidth() /
                                                                 2,
                                                                 getIconHeight() /
                                                                 2,
                                                                 17);
    }

    class ComboBoxRenderer extends JLabel implements ListCellRenderer
    {

        private Font missingIconFont;

        ComboBoxRenderer()
        {
            setOpaque(true);
            setHorizontalAlignment(LEFT);
            setVerticalAlignment(CENTER);
        }

        /**
         * This method finds the image and text corresponding to the selected
         * value and returns the label, set up to display the text and image.
         */
        @Override
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus)
        {
            if (isSelected)
            {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }
            else
            {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            String shapeString = value != null ?
                                 value.toString() :
                                 getSelectedItem().toString();
            Icon icon = name2Icon.get(shapeString);
            setIcon(icon);
            if (icon != null)
            {
                setText(shapeString);
                setFont(list.getFont());
            }
            else
            {
                setMissingIconText(shapeString + NbBundle.getMessage(
                                   CLAZZ,
                                   "GradientTypeComboBox.noImageAvailable",
                                   shapeString),
                                   list.getFont());
            }

            return this;
        }

        /**
         * Set the font and text when no image was found.
         */
        protected void setMissingIconText(String missingIconText,
                                          Font normalFont)
        {
            if (missingIconFont == null)
            {
                //lazily create this font
                missingIconFont = normalFont.deriveFont(Font.ITALIC);
            }
            setFont(missingIconFont);
            setText(missingIconText);
        }
    }
}
