# Arkkitehtuurikuvaus

## Rakenne

Ohjelman koodin pakkausrakenne on seuraava

![Pakkauskuvaus](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskuvaus.png)

Pakkaus _icehockeystats_ pitää sisällään käyttöliittymän kontrollitoiminnot. Pakkaus _icehockeystats.domain_ pitää sisällään sovelluslogiikan ja keskeiset luokat ja pakkaus _icehockeystats.dao_ sisältää tiedon luku ja tallennus toiminnot.

## Käyttöliittymä

Käyttöliittymä pitää sisällään neljä tab -välilehteä:

- Kokoonpanot: koti ja vierasjoukkue
- Tilastointi: Keskeinen ottelutapahtumien seuranta ja kontrollointi
- Maali: Maalien syöttönäkymä
- Rangaistus: Rangaistuksien syöttönäkymä

Käyttöliittymän avulla voidaan lisätä ottelutapahtumia ja liittää ne oikeisiin luokkiin (Match, Team, Player)

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat: Match, Team, Player, Goal, Penalty, Clock.
Match luokka sisältää Team (home ja away) oliot. Lisäksi Match-luokassa on lista-oliot ottelutapahtumille Goal ja Penalty. Team -luokka pitää sisällään lista-olion Player luokasta. Ja Player luokka sisältää pelaajakohtaiset tiedot (number, firstName, lastName, position, line) ja lisäksi lista-olioina pelaajaan liittyvät ottelutapahtumat Goal ja Penalty.

## Tietojen pysyväistallennus

Sovelluksen käynnistyessä ladataan joukkueiden pelaajalistat csv -tiedostosta.

Otteluun liittyvät joukkueet ja pelaajat tallennetaan tietokantaan. Lisäksi tietokantaan tallennetaan ottelun tapahtumat (maalit, rangaistukset). Tietokannassa on seuraavat taulut: Match, Team, Player, Goals, Penalties, PenaltyCodes. Pysyväistallennuksessa käytetään DAO -mallia. Tosin nykyisen sovelluksen ratkaisu tulisi olla enemmän luokkakohtainen, koska MatchDao tallentaa kaikkiin luokkiin liittyvän tiedon.

### Päätoiminnallisuudet

#### Sovelluksen käynnistys

Kun sovellus käynnistetään, niin koti- ja vierasjoukkueiden pelaajat (Nimi, pelinumero, pelipaikka, kentällinen) luetaan tekstitiedostosta käyttöliittymän tableView -näkymiin. Käyttäjä voi tarvittaessa muokata pelaajaluetteloita.

![Sekvenssikaavio](https://github.com/samilait/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/OT-Sekvenssi1.png)

#### Ottelun käynnistys

Kun ottelu käynnistetään, niin tehokas peliaika alkaa juoksemaan käyttäjän painaessa "Käynnistä" nappulaa. Kellon käydessä Clock oliota päivitetään sekunnin välein

#### Maalin lisääminen

Maalin lisäämisessä käyttäjä valitsee maalintekijät ja syöttäjät ja nämä tiedot tallennetaan sekä joukkueen, että pelaajien maali ja syöttösaldoihin.

#### Rangaistuksen lisääminen

Rangaistuksen lisäämisessä käyttäjä valitsee rikkeen tehneen pelaajan, rangaistuksen pituuden ja syyn. Nämä tiedot tallennetaan sekä joukkueen, että pelaajan rangaistussaldoihin myöhempää pysyväistallennusta varten.

#### Tiedon tallennus

Ottelukohtaiset, joukkuekohtaiset ja pelaajakohtaiset tiedot tallennetaan tietokantaan tapahtumalajeittain (maalit, rangaistukset)

## Ohjelman rakenteeseen jääneet heikkoudet

Käyttöliittymän toiminnallisuuttta voisi eriyttää enemmän. Nyt FXMLController pitää sisällään erittäin paljon toiminnallisuutta, joka voisi olla erillisessä sovelluslogiikka kokonaisuudessa.

Tietokannan rakennetta voisi vielä miettiä uudelleen.





