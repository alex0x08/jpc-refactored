package org.jpc.emulator.execution.opcodes.vm;

import org.jpc.emulator.execution.*;
import org.jpc.emulator.execution.decoder.*;
import org.jpc.emulator.processor.*;
import org.jpc.emulator.processor.fpu64.*;
import static org.jpc.emulator.processor.Processor.*;

public class jmp_Ap extends Executable
{
    final int cs, targetEip;
    final int blockLength;
    final int instructionLength;

    public jmp_Ap(int blockStart, Instruction parent)
    {
        super(blockStart, parent);
        blockLength = parent.x86Length+(int)parent.eip-blockStart;
        instructionLength = parent.x86Length;
        targetEip = parent.operand[0].ptr.off;
        cs = parent.operand[0].ptr.seg;
    }

    public Branch execute(Processor cpu)
    {
        cpu.jumpFar(cs, targetEip);
        return Branch.Jmp_Unknown;
    }

    public boolean isBranch()
    {
        return true;
    }

    public String toString()
    {
        return this.getClass().getName();
    }
}