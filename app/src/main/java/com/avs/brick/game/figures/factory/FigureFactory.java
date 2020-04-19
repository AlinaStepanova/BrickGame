package com.avs.brick.game.figures.factory;

import android.content.Context;
import android.graphics.Point;

import com.avs.brick.game.enums.FigureType;
import com.avs.brick.game.figures.Figure;
import com.avs.brick.game.figures.figure_j.JFigure;
import com.avs.brick.game.figures.figure_j.JFourthFigure;
import com.avs.brick.game.figures.figure_j.JSecondFigure;
import com.avs.brick.game.figures.figure_j.JThirdFigure;
import com.avs.brick.game.figures.figure_l.LFigure;
import com.avs.brick.game.figures.figure_l.LFourthFigure;
import com.avs.brick.game.figures.figure_l.LSecondFigure;
import com.avs.brick.game.figures.figure_l.LThirdFigure;
import com.avs.brick.game.figures.figure_long.LongFigure;
import com.avs.brick.game.figures.figure_long.LongSecondFigure;
import com.avs.brick.game.figures.figure_s.SFigure;
import com.avs.brick.game.figures.figure_z.ZSecondFigure;
import com.avs.brick.game.figures.SquareFigure;
import com.avs.brick.game.figures.figure_t.TFigure;
import com.avs.brick.game.figures.figure_t.TFourthFigure;
import com.avs.brick.game.figures.figure_t.TSecondFigure;
import com.avs.brick.game.figures.figure_t.TThirdFigure;
import com.avs.brick.game.figures.figure_z.ZFigure;
import com.avs.brick.game.figures.figure_s.SSecondFigure;

import static com.avs.brick.game.ui.main.views.PreviewAreaView.PREVIEW_AREA_WIDTH;

public class FigureFactory {

    public static Figure getFigure(FigureType figureType, int widthSquare, int scale, int squaresCountInRow, Context context) {
        switch (figureType) {
            case S_FIGURE:
                return new SFigure(widthSquare, scale, squaresCountInRow, context);
            case Z_FIGURE:
                return new ZFigure(widthSquare, scale, squaresCountInRow, context);
            case S_SECOND_FIGURE:
                return new SSecondFigure(widthSquare, scale, squaresCountInRow, context);
            case Z_SECOND_FIGURE:
                return new ZSecondFigure(widthSquare, scale, squaresCountInRow, context);
            case L_FIGURE:
                return new LFigure(widthSquare, scale, squaresCountInRow, context);
            case L_FOURTH_FIGURE:
                return new LFourthFigure(widthSquare, scale, squaresCountInRow, context);
            case L_SECOND_FIGURE:
                return new LSecondFigure(widthSquare, scale, squaresCountInRow, context);
            case L_THIRD_FIGURE:
                return new LThirdFigure(widthSquare, scale, squaresCountInRow, context);
            case J_FIGURE:
                return new JFigure(widthSquare, scale, squaresCountInRow, context);
            case J_SECOND_FIGURE:
                return new JSecondFigure(widthSquare, scale, squaresCountInRow, context);
            case J_FOURTH_FIGURE:
                return new JFourthFigure(widthSquare, scale, squaresCountInRow, context);
            case J_THIRD_FIGURE:
                return new JThirdFigure(widthSquare, scale, squaresCountInRow, context);
            case SQUARE_FIGURE:
                return new SquareFigure(widthSquare, scale, squaresCountInRow, context);
            case LONG_SECOND_FIGURE:
                return new LongSecondFigure(widthSquare, scale, squaresCountInRow, context);
            case LONG_FIGURE:
                return new LongFigure(widthSquare, scale, squaresCountInRow, context);
            case T_FIGURE:
                return new TFigure(widthSquare, scale, squaresCountInRow, context);
            case T_SECOND_FIGURE:
                return new TSecondFigure(widthSquare, scale, squaresCountInRow, context);
            case T_THIRD_FIGURE:
                return new TThirdFigure(widthSquare, scale, squaresCountInRow, context);
            case T_FOURTH_FIGURE:
                return new TFourthFigure(widthSquare, scale, squaresCountInRow, context);
            default:
                return null;
        }
    }

    public static Figure getFigure(FigureType figureType, int widthSquare, int scale, Context context, Point point) {
        switch (figureType) {
            case S_FIGURE:
                scale += 2 * widthSquare;
                return new SFigure(widthSquare, scale, context, point);
            case Z_FIGURE:
                scale += 3 * widthSquare;
                return new ZFigure(widthSquare, scale, context, point);
            case S_SECOND_FIGURE:
                scale += 2 * widthSquare;
                return new SSecondFigure(widthSquare, scale, context, point);
            case Z_SECOND_FIGURE:
                scale += 3 * widthSquare;
                return new ZSecondFigure(widthSquare, scale, context, point);
            case L_FIGURE:
                scale += 3 * widthSquare;
                return new LFigure(widthSquare, scale, context, point);
            case L_FOURTH_FIGURE:
                scale += 3 * widthSquare;
                return new LFourthFigure(widthSquare, scale, context, point);
            case L_SECOND_FIGURE:
                scale += 2 * widthSquare;
                return new LSecondFigure(widthSquare, scale, context, point);
            case L_THIRD_FIGURE:
                scale += 3 * widthSquare;
                return new LThirdFigure(widthSquare, scale, context, point);
            case J_FIGURE:
                scale += 3 * widthSquare;
                return new JFigure(widthSquare, scale, context, point);
            case J_SECOND_FIGURE:
                scale += 3 * widthSquare;
                return new JSecondFigure(widthSquare, scale, context, point);
            case J_FOURTH_FIGURE:
                scale += 3 * widthSquare;
                return new JFourthFigure(widthSquare, scale, context, point);
            case J_THIRD_FIGURE:
                scale += 3 * widthSquare;
                return new JThirdFigure(widthSquare, scale, context, point);
            case SQUARE_FIGURE:
                return new SquareFigure(widthSquare, scale, context, point);
            case LONG_SECOND_FIGURE:
                scale += 3 * widthSquare;
                return new LongSecondFigure(widthSquare, scale, context, point);
            case LONG_FIGURE:
                scale += 3 * widthSquare;
                point.x = point.x + widthSquare;
                return new LongFigure(widthSquare, scale, context, point);
            case T_FIGURE:
                scale += widthSquare;
                return new TFigure(widthSquare, scale, context, point);
            case T_SECOND_FIGURE:
                scale += 3 * widthSquare;
                return new TSecondFigure(widthSquare, scale, context, point);
            case T_THIRD_FIGURE:
                scale += 3 * widthSquare;
                return new TThirdFigure(widthSquare, scale, context, point);
            case T_FOURTH_FIGURE:
                scale += 2 * widthSquare;
                return new TFourthFigure(widthSquare, scale, context, point);
            default:
                return null;
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public static Figure getFigure(FigureType figureType, int widthSquare, Context context) {
        int center = PREVIEW_AREA_WIDTH / 2;
        switch (figureType) {
            case S_FIGURE:
                return new SFigure(widthSquare, context, new Point(center - widthSquare / 2 - widthSquare / 4, widthSquare + (widthSquare / 2)));
            case Z_FIGURE:
                return new ZFigure(widthSquare, context, new Point(center - widthSquare / 2 - widthSquare / 4, widthSquare));
            case S_SECOND_FIGURE:
                return new SSecondFigure(widthSquare, context, new Point(center - widthSquare / 2, widthSquare + (widthSquare / 2)));
            case Z_SECOND_FIGURE:
                return new ZSecondFigure(widthSquare, context, new Point(center - widthSquare / 2, widthSquare));
            case L_FIGURE:
                return new LFigure(widthSquare, context, new Point(center - widthSquare / 4, widthSquare));
            case L_FOURTH_FIGURE:
                return new LFourthFigure(widthSquare, context, new Point(center - widthSquare / 2 - widthSquare / 4, widthSquare));
            case L_SECOND_FIGURE:
                return new LSecondFigure(widthSquare, context, new Point(center - widthSquare / 2 - widthSquare / 4, widthSquare + (widthSquare / 2)));
            case L_THIRD_FIGURE:
                return new LThirdFigure(widthSquare, context, new Point(center - widthSquare / 2, widthSquare));
            case J_FIGURE:
                return new JFigure(widthSquare, context, new Point(center - widthSquare / 2, widthSquare));
            case J_SECOND_FIGURE:
                return new JSecondFigure(widthSquare, context, new Point(center - widthSquare / 2 - widthSquare / 4, widthSquare));
            case J_FOURTH_FIGURE:
                return new JFourthFigure(widthSquare, context, new Point(center - widthSquare / 2 - widthSquare / 4, widthSquare));
            case J_THIRD_FIGURE:
                return new JThirdFigure(widthSquare, context, new Point(center - widthSquare / 2, widthSquare));
            case SQUARE_FIGURE:
                return new SquareFigure(widthSquare, context, new Point(center - widthSquare / 2, widthSquare));
            case LONG_SECOND_FIGURE:
                return new LongSecondFigure(widthSquare, context, new Point(center - widthSquare, widthSquare));
            case LONG_FIGURE:
                return new LongFigure(widthSquare, context, new Point(center - widthSquare / 4, widthSquare));
            case T_FIGURE:
                return new TFigure(widthSquare, context, new Point(center - widthSquare / 2 - widthSquare / 4, widthSquare * 2));
            case T_SECOND_FIGURE:
                return new TSecondFigure(widthSquare, context, new Point(center - widthSquare / 2 - widthSquare / 4, widthSquare));
            case T_THIRD_FIGURE:
                return new TThirdFigure(widthSquare, context, new Point(center - widthSquare / 2, widthSquare));
            case T_FOURTH_FIGURE:
                return new TFourthFigure(widthSquare, context, new Point(center - widthSquare / 2, widthSquare + (widthSquare / 2)));
            default:
                return null;
        }
    }
}
