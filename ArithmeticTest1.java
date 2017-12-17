import java.io.*;
import java.util.Date;
import java.util.Random;
public class SZYS
{
  public static void main(String[] args)throws Exception
  {
    int number,score=0,operator=0;
    final int add=0, sub=1, mul=2, div=3, ran=4;
    final String operation[]={"��","��","��","��"};
    //ʮ�����е��������ڴ��10��  ÿ�зֱ�Ϊ  �����1 ����� �����2 �� �û���
    
    int question[][] = new int[10][5];
    int temp;             //temp ֻ��Ϊ����ʱ��ת������ �����ط�û������
    
    Random numberGenerator = new Random(new Date().getTime());
    if     (args[0].trim().toUpperCase().equals("+"))
      operator=add;
    else if(args[0].trim().toUpperCase().equals("-"))
      operator=sub;
    else if(args[0].trim().toUpperCase().equals("X"))
      operator=mul;
    else if(args[0].trim().toUpperCase().equals("/"))
      operator=div;
    else if(args[0].trim().toUpperCase().equals("R"))
      operator=ran;
    else if(args.length<2)
    {
        System.out.println("�����ӦΪ+-X/R֮һ�����������г���");
	return;
    }
    
    number = Integer.parseInt(args[1]);    //������λ�����浽number   int������

    BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));
    //�����������ʹ���߿��Լ����
    for(int i=1; i<=10; i++)
    { 
      if(operator==ran)
        question[i-1][1]=numberGenerator.nextInt(4);
      else  
	question[i-1][1]=operator;                 //���������
	  
      question[i-1][0]=numberGenerator.nextInt((int)Math.pow(10,number));  //����λ���ڴ������еõ�����    //��һ����
      question[i-1][2]=numberGenerator.nextInt((int)Math.pow(10,number));      //�ڶ�����
      //�����һ����ڶ��������
	  
      //��������е��������
      if(question[i-1][1]==sub && question[i-1][0] < question[i-1][2])
      {
         temp = question[i-1][0];
	 question[i-1][0] = question[i-1][2];
	 question[i-1][2] = temp;
      }                                                               //ͨ������tempʵ�ּ����뱻������λ�ý���
      while(question[i-1][1]==div && question[i-1][2]==0)
      {
	question[i-1][2]=numberGenerator.nextInt((int)Math.pow(10,number));  //����ѭ����������ֱ��while�ж�������Ϊ0  
      }
    //���濪ʼ��ӡ��Ŀ
      System.out.print("��"+i+"��: " + question[i-1][0] + operation[question[i-1][1]] + question[i-1][2] +" = ");     	
	
      question[i-1][4] = Integer.parseInt(keyboardIn.readLine());  //����������Ĵ��浽question[i-1][4] ��
	
	  switch (question[i-1][1])
	  {
	     case add:
               question[i-1][3] = question[i-1][0]+question[i-1][2];
               if(question[i-1][4] == question[i-1][3])
                 score += 10;
			   break;

	     case sub:
               question[i-1][3] = question[i-1][0]-question[i-1][2];
               if(question[i-1][4] == question[i-1][3])
                 score += 10;
               break;			 
	    
             case mul:
               question[i-1][3] = question[i-1][0]*question[i-1][2];
               if(question[i-1][4] == question[i-1][3])
                 score += 10;
			   break;
			   
             case div:
               question[i-1][3] = question[i-1][0]/question[i-1][2];
               if(question[i-1][4] == question[i-1][3])
                 score += 10;	
			   break;
	  }
	}
	keyboardIn.close();   //�رռ���������
	
	System.out.println("�㱾�ε���ϰ���Ϊ(���е����ڶ���Ϊ��׼�𰸣����һ��Ϊ��Ĵ�)��");
	for(int i=1; i<=10; i++)
        {
	  String forprint="%1$"+number+"d"+operation[question[i-1][1]] +"%2$"+number+"d = %3$"+(number+2)+"d %4$"+(number+2)+"d\n";
	  System.out.printf(forprint,question[i-1][0],question[i-1][2],question[i-1][3],question[i-1][4]);//ʹ�ø�ʽ���
	}   	
	System.out.println("�㱾����ϰ�ĵ÷�Ϊ��"+score+"��");
  } 
}