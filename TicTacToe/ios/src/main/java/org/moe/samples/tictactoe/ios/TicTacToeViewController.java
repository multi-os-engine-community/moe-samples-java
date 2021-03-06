// Copyright (c) 2015, Intel Corporation
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
// 1. Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above
// copyright notice, this list of conditions and the following disclaimer
// in the documentation and/or other materials provided with the
// distribution.
// 3. Neither the name of the copyright holder nor the names of its
// contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package org.moe.samples.tictactoe.ios;

import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.Owned;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ObjCRuntime;
import org.moe.natj.objc.ann.IBAction;
import org.moe.natj.objc.ann.IBOutlet;
import org.moe.natj.objc.ann.ObjCClassName;
import org.moe.natj.objc.ann.Property;
import org.moe.natj.objc.ann.Selector;
import org.moe.samples.tictactoe.common.TicTacToe;

import apple.NSObject;
import apple.quartzcore.CALayer;
import apple.uikit.UIButton;
import apple.uikit.UIColor;
import apple.uikit.UILabel;
import apple.uikit.UIViewController;
import apple.uikit.enums.UIControlState;

@org.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("TicTacToeViewController")
@RegisterOnStartup
public class TicTacToeViewController extends UIViewController {

    @Owned
    @Selector("alloc")
    public static native TicTacToeViewController alloc();

    @Selector("init")
    public native TicTacToeViewController init();

    protected TicTacToeViewController(Pointer peer) {
        super(peer);
    }

    private TicTacToe ticTacToeGame;

    public UILabel statusText = null;

    public UIButton gameButton0 = null;
    public UIButton gameButton1 = null;
    public UIButton gameButton2 = null;

    public UIButton gameButton3 = null;
    public UIButton gameButton4 = null;
    public UIButton gameButton5 = null;

    public UIButton gameButton6 = null;
    public UIButton gameButton7 = null;
    public UIButton gameButton8 = null;

    @Override
    public void viewDidLoad() {
        ticTacToeGame = new TicTacToe();
        statusText = getStatusTextLabel();

        gameButton0 = getGameButton0();
        gameButton1 = getGameButton1();
        gameButton2 = getGameButton2();

        gameButton3 = getGameButton3();
        gameButton4 = getGameButton4();
        gameButton5 = getGameButton5();

        gameButton6 = getGameButton6();
        gameButton7 = getGameButton7();
        gameButton8 = getGameButton8();

        startNewGame();
    }

    @IBOutlet
    @Selector("statusText")
    @Property
    public native UILabel getStatusTextLabel();

    @IBOutlet
    @Selector("gameButton0")
    @Property
    public native UIButton getGameButton0();

    @IBOutlet
    @Selector("gameButton1")
    @Property
    public native UIButton getGameButton1();

    @IBOutlet
    @Selector("gameButton2")
    @Property
    public native UIButton getGameButton2();

    @IBOutlet
    @Selector("gameButton3")
    @Property
    public native UIButton getGameButton3();

    @IBOutlet
    @Selector("gameButton4")
    @Property
    public native UIButton getGameButton4();

    @IBOutlet
    @Selector("gameButton5")
    @Property
    public native UIButton getGameButton5();

    @IBOutlet
    @Selector("gameButton6")
    @Property
    public native UIButton getGameButton6();

    @IBOutlet
    @Selector("gameButton7")
    @Property
    public native UIButton getGameButton7();

    @IBOutlet
    @Selector("gameButton8")
    @Property
    public native UIButton getGameButton8();

    @IBAction
    @Selector("newGame:")
    public void newGame(NSObject sender) {
        startNewGame();
    }

    @IBAction
    @Selector("gameButtonAction:")
    public void gameButtonAction(UIButton sender) {
        int btn = (int) sender.tag();
        makeMove(btn, "X");
        ticTacToeGame.newTurn(btn, TicTacToe.playerX);
        ticTacToeGame.minimax(0, TicTacToe.player0);
        ticTacToeGame.newTurn(ticTacToeGame.getComputersMove(), TicTacToe.player0);
        makeMove(ticTacToeGame.getComputersMove(), "0");
        checkGameOver();
    }

    public void startNewGame() {
        statusText.setText("Status: New Game, your turn...");
        statusText.setTextColor(UIColor.colorWithRedGreenBlueAlpha((float) 0 / 255.0, (float) 0 /
                255.0, (float) 0 / 255.0, (float) 1.0));
        ticTacToeGame.startNewGame();

        setButtonToStartState(gameButton0, 0);
        setButtonToStartState(gameButton1, 1);
        setButtonToStartState(gameButton2, 2);

        setButtonToStartState(gameButton3, 3);
        setButtonToStartState(gameButton4, 4);
        setButtonToStartState(gameButton5, 5);

        setButtonToStartState(gameButton6, 6);
        setButtonToStartState(gameButton7, 7);
        setButtonToStartState(gameButton8, 8);
    }

    private void setButtonToStartState(UIButton button, int tag) {
        button.setTitleForState("", UIControlState.Normal);
        button.setEnabled(true);
        button.setTag(tag);
        final CALayer layer = button.layer();
        layer.setCornerRadius(6.0);
    }

    public void checkGameOver() {
        if (ticTacToeGame.isGameOver()) {
            gameButton0.setEnabled(false);
            gameButton1.setEnabled(false);
            gameButton2.setEnabled(false);

            gameButton3.setEnabled(false);
            gameButton4.setEnabled(false);
            gameButton5.setEnabled(false);

            gameButton6.setEnabled(false);
            gameButton7.setEnabled(false);
            gameButton8.setEnabled(false);

            if (ticTacToeGame.wonX()) {
                statusText.setText("Status: Congratulation! You win!");
                statusText.setTextColor(UIColor.colorWithRedGreenBlueAlpha((float) 50.0 / 255.0,
                        (float) 205.0 / 255.0, (float) 50.0 / 255.0, (float) 1.0));
            } else if (ticTacToeGame.won0()) {
                statusText.setText("Status: Unfortunately, you lost!");
                statusText.setTextColor(UIColor.colorWithRedGreenBlueAlpha((float) 255.0 / 255.0,
                        (float) 0 / 255.0, (float) 0 / 255.0, (float) 1.0));
            } else {
                statusText.setText("Status: It's a draw!");
                statusText.setTextColor(UIColor.colorWithRedGreenBlueAlpha((float) 218.0 / 255.0,
                        (float) 165.0 / 255.0, (float) 32.0 / 255.0, (float) 1.0));
            }
        } else {
            statusText.setText("Status: Your turn...");
            statusText.setTextColor(UIColor.colorWithRedGreenBlueAlpha((float) 0.0 / 255.0,
                    (float) 0.0 / 255.0, (float) 0.0 / 255.0, (float) 1.0));
        }
    }

    private void makeMove(int index, String text) {
        switch (index) {
            case 0:
                gameButton0.setTitleForState(text, UIControlState.Normal);
                gameButton0.setEnabled(false);
                break;
            case 1:
                gameButton1.setTitleForState(text, UIControlState.Normal);
                gameButton1.setEnabled(false);
                break;
            case 2:
                gameButton2.setTitleForState(text, UIControlState.Normal);
                gameButton2.setEnabled(false);
                break;
            case 3:
                gameButton3.setTitleForState(text, UIControlState.Normal);
                gameButton3.setEnabled(false);
                break;
            case 4:
                gameButton4.setTitleForState(text, UIControlState.Normal);
                gameButton4.setEnabled(false);
                break;
            case 5:
                gameButton5.setTitleForState(text, UIControlState.Normal);
                gameButton5.setEnabled(false);
                break;
            case 6:
                gameButton6.setTitleForState(text, UIControlState.Normal);
                gameButton6.setEnabled(false);
                break;
            case 7:
                gameButton7.setTitleForState(text, UIControlState.Normal);
                gameButton7.setEnabled(false);
                break;
            case 8:
                gameButton8.setTitleForState(text, UIControlState.Normal);
                gameButton8.setEnabled(false);
                break;
        }
    }
}
