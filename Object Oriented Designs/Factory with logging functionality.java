// extends should go before implements:
Factory
interface PM {
  void setPassword();
}

class PMBASE implements PM{
  void setPassword(){};
}


class PMDecorator implements PM {
  PMBASE pm;
  PMDecorator(PMBASE pm);
  void setPassword () {
    log(); // decorate add fucntionality 
    pm.setPassword();
  };
}

class PMCore extends PMBASE implements PM{
 void setPassword () {};
}

class PMSSOCore extends PMBASE implements PM{
  void setPassword () {};
}


class FactoryPM {
  static PMDecorator getPM(){
      new PMDecorator(PMSSOCore/PMCore);
  }
}
